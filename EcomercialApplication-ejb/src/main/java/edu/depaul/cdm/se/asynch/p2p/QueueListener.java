package edu.depaul.cdm.se.asynch.p2p;


import com.depaul.cdm.se.yuxi.ejb.CustomerLocalBean;
import com.depaul.cdm.se.yuxi.ejb.impl.SimpleGreeterBean;
import edu.depaul.cdm.se.asynch.standalone.jms.p2p.Receiver;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@MessageDriven(mappedName = "jms/jQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class QueueListener implements MessageListener {
    private static final Logger logger = Logger.getLogger(QueueListener.class.getName());

    @EJB
    private SimpleGreeterBean greeter;
    @EJB
    private CustomerLocalBean clb;

    @Override
    public void onMessage(Message message) {
         TextMessage msg = (TextMessage) message;
         /*
       try {
            String msg2Process = msg.getText();
            System.out.println("Processing: " + msg2Process);
            StringTokenizer tokenizer = new StringTokenizer(msg2Process, ";");
            Long fromAccount = Long.parseLong(tokenizer.nextToken());
            Long toAccount = Long.parseLong(tokenizer.nextToken());
            Float amount = Float.parseFloat(tokenizer.nextToken());
            accountService.transferFunds(fromAccount, toAccount, amount);
        } catch (Exception ex) {
            Logger.getLogger(TransferFunds.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(message.toString() + " was processed");
    }
       */      System.out.println(msg);
        try {
            
             String msg2Process = msg.getText();
              logger.log(Level.INFO, "Request.: {0}", new Object[]{msg});
            logger.log(Level.INFO, "Response: {0}", new Object[]{greeter.greetMe(msg2Process)});
            System.out.println("Processing: " + msg2Process);
            StringTokenizer tokenizer = new StringTokenizer(msg2Process, ";");
          //  String msg = "Deposit;100000;1";
            String operation=tokenizer.nextToken();
            System.out.println("Processing: " + operation);
            double amount=Double.parseDouble(tokenizer.nextToken());
            System.out.println("Processing: " + amount);
            Long customerId=Long.parseLong(tokenizer.nextToken());
            System.out.println("Processing: " + customerId);
            boolean ifSuc=false;
          
            if(operation.equals("DEPOSIT"))
            {
                ifSuc=clb.depositBalance(customerId, amount);
               
                  System.out.println("Processing: " + ifSuc);
            }
            else if(operation.equals("WITHDRAW"))
            {
                ifSuc=clb.withdrawBalance(customerId, amount);
                  System.out.println("Processing: " + ifSuc);
            }
          //  accountService.transferFunds(fromAccount, toAccount, amount);
           
             Context jndiContext = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/ConnectionFactory");
        Queue queue = (Queue) jndiContext.lookup("jms/jQueue");

        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(queue);
        connection.start();
        System.out.println("P2P Dr. Fraiser is listening...");
        while (true) {
            TextMessage consumerMessage = (TextMessage) consumer.receive();
            String request = consumerMessage.getText();
            System.out.println("Message received: " + request);

            Destination replyDestination = message.getJMSReplyTo();

            // Check to see if reply was requested
            if (replyDestination != null) {
                try (JMSContext context = connectionFactory.createContext()) {

                    // prepare response 
                    TextMessage replyMessage
                            = context.createTextMessage(""+ifSuc);

                    // send response 
                    context.createProducer().send(replyDestination, replyMessage);
                }
            }
        }
           
        } catch (JMSException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(QueueListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
