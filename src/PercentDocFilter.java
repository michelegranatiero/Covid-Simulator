import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class PercentDocFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        int len = string.length();
        boolean isValidInteger = true;
        Document document = fb.getDocument ();
        String fullText = new StringBuilder (document.getText (0, document.getLength ())).replace (offset, offset + len, string).toString ();
        int fullNumber = Integer.parseInt(fullText);

        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(string.charAt(i))) {
                isValidInteger = false;
                break;
            }
        }
        if (isValidInteger){
            if(!(fullNumber>0 && fullNumber<=100)){
                isValidInteger = false;
            }
            if(isValidInteger){
                super.insertString(fb, offset, string, attr);
            }
        } else
            Toolkit.getDefaultToolkit().beep();
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

        int len = text.length();
        boolean isValidInteger = true;
        Document document = fb.getDocument ();
        String fullText = new StringBuilder (document.getText (0, document.getLength ())).replace (offset, offset + len, text).toString ();
        int fullNumber = Integer.parseInt(fullText);

        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(text.charAt(i))) {
                isValidInteger = false;
                break;
            }
        }
        if (isValidInteger){
            if(!(fullNumber>0 && fullNumber<=100)){
                isValidInteger = false;
            }
            if(isValidInteger){
                super.replace(fb, offset, length, text, attrs);
            }
        } else
            Toolkit.getDefaultToolkit().beep();
    }
}
