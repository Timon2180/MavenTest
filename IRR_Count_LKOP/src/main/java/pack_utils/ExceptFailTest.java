package pack_utils;

public class ExceptFailTest extends Throwable
{
	private static final long serialVersionUID = 1L;
	
	String sMessageText;
	public ExceptFailTest(String sMessage)
	{
		this.sMessageText = sMessage;
	}
	
	public String toString()
	{
		return "���� ��������, ������� "+sMessageText;
	}
}
