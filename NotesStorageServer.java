public class NotesStorageServer implements java.io.Serializable
{
    public String Username;
    public String Notes;
    public int operation;
    
    public NotesStorageServer(String U,String N, int O)
    {
        Username = U ;
        Notes = N;
        operation = O;
    }
    
    
    public String getUser()
    {
        return Username;
    }
    
    public String getNotes()
    {
        return Notes;
    }
    
}