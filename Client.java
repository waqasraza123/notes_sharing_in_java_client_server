import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.util.Scanner;

public class Client {
	


	public static NotesStorageServer Input()
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("1: New Note \n 2: Old Notes");

		int operation = Integer.parseInt(sc.nextLine());
		System.out.println("username: ?");
		String username = sc.nextLine();
		NotesStorageServer newRec;
		
		if(operation == 1)
		{
			System.out.println("notes: ?");
			
			String notes = sc.nextLine();
			newRec = new NotesStorageServer(username,notes,1);

		}
		else
		{
			newRec = new NotesStorageServer(username,"",2);
		}
		return newRec;
	}



	public static void main(String[] args) 
	{
		try
		{	
			Socket sender = new Socket("localhost",12000);
			NotesStorageServer newRec = Input();
			ObjectOutputStream outServer = new ObjectOutputStream(sender.getOutputStream());
			ObjectInputStream inServer = new ObjectInputStream(sender.getInputStream());
			
			outServer.writeObject(newRec);

			if(newRec.operation == 2)
			{
				while(true)
				{
					NotesStorageServer newRec1 = (NotesStorageServer)inServer.readObject();
					System.out.println("User Found: ");
					System.out.println("User Name: "+newRec1.Username);
					System.out.println("Notes : "+ newRec1.Notes);
				}
			}

			sender.close();   
		}
		catch(Exception e)
		{
			System.out.println("Exception");
			System.out.println(e.getMessage());
		}
	}

}