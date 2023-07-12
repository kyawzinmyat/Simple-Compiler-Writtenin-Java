package lexer;

public class RealNumber extends Token{
	public final float value;
	public RealNumber(float v) {
		super(Tag.REAL);
		value = v;
	}
	public String toString() {
		return "" + value;
	}
}
