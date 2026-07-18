package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

// Este filtro solo deja pasar dígitos y un único punto decimal
public class SoloNumerosFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (esValido(string, fb)) super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (esValido(text, fb)) super.replace(fb, offset, length, text, attrs);
    }

    private boolean esValido(String texto, FilterBypass fb) throws BadLocationException {
        String contenidoActual = fb.getDocument().getText(0, fb.getDocument().getLength());
        if (texto.equals(".") && contenidoActual.contains(".")) return false;
        return texto.matches("^[0-9.]*$");
    }
}
