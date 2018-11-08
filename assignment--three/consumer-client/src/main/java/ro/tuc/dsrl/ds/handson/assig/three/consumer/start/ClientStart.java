package ro.tuc.dsrl.ds.handson.assig.three.consumer.start;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import ro.tuc.dsrl.ds.handson.assig.three.consumer.connection.QueueServerConnection;
import ro.tuc.dsrl.ds.handson.assig.three.consumer.service.EncryptionTools;
import ro.tuc.dsrl.ds.handson.assig.three.consumer.service.MailService;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 *          Distributed Systems, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-one-client
 * @Since: Sep 1, 2015
 * @Description:
 *	Starting point for the Consumer Client application. This application
 *  will run in an infinite loop and retrieve messages from the queue server
 *  and send e-mails with them as they come.
 */
public class ClientStart {

	private ClientStart() {
	}

	public static void main(String[] args) throws Exception {
		
		//encryption related
		String k = "Bar12345Bar12345";
		SecretKey key = new SecretKeySpec(k.getBytes(), "AES");
        EncryptionTools encrypter = new EncryptionTools(key);
        
		QueueServerConnection queue = new QueueServerConnection("localhost",8888);
		MailService mailService = new MailService("mpop993@gmail.com",encrypter.decrypt("OXF74PCOzeHuVus3YVc6xg=="));
		String message;

		while(true) {
			try {
				message = queue.readMessage();
				System.out.println("DVD information : "+message);
				mailService.sendMail("mrmariuspop@gmail.com","New DVD Added",message);
				
				//print to file
				 FileWriter fileWriter = new FileWriter("message.txt");
				    PrintWriter printWriter = new PrintWriter(fileWriter);
				    printWriter.printf(message);
				    printWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
