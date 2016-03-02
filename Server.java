import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {


	public static void main(String[] args)
	{
		ServerSocket listener = null;
		try{
			ArrayList<NotesStorageServer> list = new ArrayList<NotesStorageServer>();
			listener = new ServerSocket(12000);
			while(true)
			{
				Socket socket = listener.accept();
				ObjectOutputStream  OutClient = new ObjectOutputStream (socket.getOutputStream());
				ObjectInputStream inClient = new ObjectInputStream(socket.getInputStream());
				NotesStorageServer newRec;
				newRec = (NotesStorageServer)inClient.readObject();

				if(newRec.operation == 1){
					list.add(newRec);
					System.out.println("Server Got: ");
					System.out.println("User: " + newRec.Username);
					System.out.println("Notes: " + newRec.Notes);
				}
				else
				{
					for(int i = 0; i < list.size();i++)
					{
						if(list.get(i).Username.equals(newRec.Username))
						{
							OutClient.writeObject(list.get(i));
						}
						else {
							System.out.println("User Not Found!");
						}
					}


				}




			}

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}


	}

}