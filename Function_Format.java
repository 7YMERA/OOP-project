import java.awt.Font;

public class Function_Format {

    GUI gui;
    Font arial, comicSansMs, timesNewRoman , impact, lucidaHandwriting;
    String selectedfont;

    public Function_Format(GUI gui) {
        this.gui = gui;
    }

    public void createFont(int fontSize) {

        arial = new Font("Arial", Font.PLAIN, fontSize);
        comicSansMs = new Font("Comic Sans MS", Font.PLAIN, fontSize);
        timesNewRoman = new Font("Times New Roman", Font.PLAIN, fontSize);
        impact = new Font("Impact", Font.PLAIN, fontSize);
        lucidaHandwriting = new Font("Lucida Handwriting", Font.PLAIN, fontSize);
        
        setFont(selectedfont);
    }
    public void setFont(String font) {

        selectedfont = font;

        switch(selectedfont) {
            case "Arial": 
                gui.textArea.setFont(arial); break;
            case "Comic Sans MS":
                 gui.textArea.setFont(comicSansMs); break;
            case "Times New Roman": 
                gui.textArea.setFont(timesNewRoman); break;
            case "Impact":
                gui.textArea.setFont(impact); break;
            case "Lucida Handwriting":
                gui.textArea.setFont(lucidaHandwriting); break;
        }
    }

    
}