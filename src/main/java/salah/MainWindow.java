package salah;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Salah salah;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/MarcusImage.png"));
    private Image salahImage = new Image(this.getClass().getResourceAsStream("/images/SalahImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Salah instance */
    public void setSalah(Salah s) {
        salah = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Salah's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = salah.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSalahDialog(response, salahImage)
        );
        userInput.clear();
    }
}

