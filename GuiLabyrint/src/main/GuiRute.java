package main;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;

public class GuiRute extends Button{
	GuiRute()
	{
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                null, new BorderWidths(1))));
	        int storrelse;
	        storrelse = 0;
            setMinWidth(storrelse);
            setMinHeight(storrelse);
	}

}
