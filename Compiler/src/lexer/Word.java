package lexer;

public class Word extends Token{
	public final String lexeme;
	public static final Word 
			and = new Word(Tag.AND, "&&"),
			or  = new Word(Tag.OR, "||"),
			eq  = new Word(Tag.EQ, "=="),
			ne  = new Word(Tag.NE, "!="),
			le  = new Word(Tag.LE, "<="),
			ge  = new Word(Tag.GE, ">=");
	
	public Word(int t, String s) {
		super(t);
		lexeme = s;
	}

	public String toString() {
		return lexeme;
	}
}
