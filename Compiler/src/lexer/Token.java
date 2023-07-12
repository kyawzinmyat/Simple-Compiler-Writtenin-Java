package lexer;

public class Token {
	
	int tag;
	
	public Token(int t) {
		tag = t;
	}
	
	public String toString()
	{
		return "" + (char) tag;
	}
}
