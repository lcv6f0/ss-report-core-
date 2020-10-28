/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Core.Sequences;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.sound.midi.Sequence;

/**
 * FXML Controller class
 *
 * @author Christian
 */
public class MainController implements Initializable {
    //seq 1

    @FXML
    private TextField seq1_opening_disel_p1, seq1_opening_g1_p1, seq1_opening_g2_p1,
            seq1_opening_disel_p2, seq1_opening_g1_p2, seq1_opening_g2_p2, seq1_closing_disel_p1,
            seq1_closing_g1_p1, seq1_closing_g2_p1, seq1_closing_disel_p2, seq1_closing_g1_p2,
            seq1_closing_g2_p2;
    @FXML
    private TextField seq2_opening_disel_p1, seq2_opening_g1_p1, seq2_opening_g2_p1,
            seq2_opening_disel_p2, seq2_opening_g1_p2, seq2_opening_g2_p2, seq2_closing_disel_p1,
            seq2_closing_g1_p1, seq2_closing_g2_p1, seq2_closing_disel_p2, seq2_closing_g1_p2,
            seq2_closing_g2_p2;

    //seq 1
    @FXML
    private TextField seq1_opening_disel_p3, seq1_opening_g1_p3, seq1_opening_g2_p3,
            seq1_opening_disel_p4, seq1_opening_g1_p4, seq1_opening_g2_p4, seq1_closing_disel_p3,
            seq1_closing_g1_p3, seq1_closing_g2_p3, seq1_closing_disel_p4, seq1_closing_g1_p4,
            seq1_closing_g2_p4;
    @FXML
    private TextField seq2_opening_disel_p3, seq2_opening_g1_p3, seq2_opening_g2_p3,
            seq2_opening_disel_p4, seq2_opening_g1_p4, seq2_opening_g2_p4, seq2_closing_disel_p3,
            seq2_closing_g1_p3, seq2_closing_g2_p3, seq2_closing_disel_p4, seq2_closing_g1_p4,
            seq2_closing_g2_p4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void save() {
    
    }

}
