package lexer;
import java.io.*;
import java.util.*;

public class Lexer {
	public int line = 1;
	private char peek = ' ';
	private Hashtable words = new Hashtable();
	
	void reserve(Word w) {
		words.put(w.lexeme, w);
	}
	
	public Lexer() {
		reserve(new Word(Tag.TRUE, "true"));
		reserve(new Word(Tag.FALSE, "false"));
	}
	
	public Token scan() throws IOException{
		for(;;peek = (char) System.in.read()) {
			if (peek == ' ' || peek == '\t') continue;
			else if (peek == '\n') ++line;
			else break;
		}
		
		if (Character.isDigit(peek)) {
			int v = 0;
			do {
				v = 10 * v + Character.digit(peek, 10);
				peek = (char) System.in.read();
			}
			while(Character.isDigit(peek));
			return new Number(v);
		}
		
		if (Character.isLetter(peek)) {
			StringBuffer b = new StringBuffer();
			do {
				b.append(peek);
				peek = (char) System.in.read();
			}
			while (Character.isLetter(peek));
			
		}
		
		Token t = new Token(peek);
		return t;
	}
	
	public static void main(String[] args)
	{
		Lexer l = new Lexer();
		Number t;
		try {
			t = (Number) l.scan();
			System.out.println(t.value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
