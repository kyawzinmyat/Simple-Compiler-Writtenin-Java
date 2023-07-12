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
		
		switch (peek){
			case '/':
				handleComment();
				return null;
			case '&':
				if (readChar('&')) return Word.and;
				return new Token('&');
		}
		
		if (Character.isDigit(peek)) {
			int v = 0;
			do {
				v = 10 * v + Character.digit(peek, 10);
				readChar();
			}
			while(Character.isDigit(peek));
			if (peek != '.') {
				return new Number(v);

			}
			float f = v; float d = 10;
			for (;;) {
				readChar();
				if (!Character.isDigit(peek)) break;
				f = f + Character.digit(peek, 10)/d;
				d *= 10;
			}
			return new RealNumber(f);
		}
		
		if (Character.isLetter(peek)) {
			StringBuffer b = new StringBuffer();
			do {
				b.append(peek);
				readChar();
			}
			while (Character.isLetter(peek));
			String s = b.toString();
			Word w = (Word) words.get(s);
			if (w != null) return w;
			w = new Word(Tag.IDENTIFIER, s);
			words.put(s, w);
			return w;
		}
		
		Token t = new Token(peek);
		return t;
	}
	
	private void handleComment() throws IOException {
		if (readChar('/')) {
			do {
				readChar();
			}
			while(peek != '\n');
			++line;
		}	
		else if (readChar('*')) {
			do {
				readChar();
				if (peek=='\n') ++line;
			}
			while(peek != '*');
			if (readChar('/')) ++ line;
		}	
	}
	
	private void readChar() throws IOException {
		peek = (char) System.in.read();
	}
	
	private boolean readChar(char c) throws IOException{
		readChar();
		if (peek != c) return false;
		peek = ' ';
		return true;
	}
	
	public static void main(String[] args)
	{
		Lexer l = new Lexer();
		System.out.println("hello");
		Token t;
		while (true)
		{
			try {
				t = l.scan();
				System.out.println(t);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}
}
