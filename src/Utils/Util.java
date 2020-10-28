package Utils;

import Core.DBConnect;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Util {

    public static Properties properties = null;

    public static void clearAllFieds(Object patient_TabbedPane) {
        //    System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
        ArrayList<Node> allNodes;

        allNodes = getAllNodes((Parent) patient_TabbedPane);
        // System.out.println(allNodes.size());
        allNodes.stream().map((comp) -> {
            if (comp instanceof TextField) {
                TextField textField = (TextField) comp;
                textField.setText("");
            } //End of clearing all text fields
            return comp;
        }).map((comp) -> {
            if (comp instanceof TextArea) {
                TextArea textArea = (TextArea) comp;
                textArea.setText("");
            } // end of clearing all text Areas
            return comp;
        }).map((comp) -> {
            if (comp instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) comp;
                checkBox.setSelected(false);
            } // end of clearing all check boxes
            // end of clearing all combo Boxes
            return comp;
        }).filter((comp) -> (comp instanceof ComboBox)).forEachOrdered((comp) -> {
            ComboBox comboBox = (ComboBox) comp;
            comboBox.setValue("");
        });
    }

    /**
     * Return a singlec
     *
     * @param query
     * @param dbconnect
     * @return
     */
    public static List<String> getAllId(String query, DBConnect dbconnect) {
        List l = new ArrayList();

        ResultSet rs = null;
        try {

            rs = dbconnect.getStatement().executeQuery((query));

            while (rs.next()) {
                l.add(rs.getString(1));
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }

        return l;
    }

    public static void clearAllFiedsNotCombo(Object patient_TabbedPane) {
        //    System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
        ArrayList<Node> allNodes;

        allNodes = getAllNodes((Parent) patient_TabbedPane);

        allNodes.stream().map((comp) -> {
            if (comp instanceof TextField) {
                TextField textField = (TextField) comp;
                textField.setText("");
            } //End of clearing all text fields
            return comp;
        }).map((comp) -> {
            if (comp instanceof TextArea) {
                TextArea textArea = (TextArea) comp;
                textArea.setText("");
            } // end of clearing all text Areas
            return comp;
        }).map((comp) -> {
            if (comp instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) comp;
                checkBox.setSelected(false);
            } // end of clearing all check boxes
            // end of clearing all combo Boxes
            return comp;
        });
    }

    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<>();
        addAllDescendents(root, nodes);
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        parent.getChildrenUnmodifiable().stream().map((node) -> {
            nodes.add(node);
            return node;
        }).filter((node) -> (node instanceof Parent)).forEachOrdered((node) -> {
            addAllDescendents((Parent) node, nodes);
        });
    }

    public static void restoreFieldsBackgroundColor(Node patient_TabbedPane) {
        ArrayList<Node> allNodes = getAllNodes((Parent) patient_TabbedPane);
        allNodes.stream().filter((allNode) -> (allNode instanceof TextField || allNode instanceof CheckBox
                || allNode instanceof TextArea || allNode instanceof DatePicker || allNode instanceof ComboBox)).forEachOrdered((allNode) -> {
            try {

                //  textField.setStyle("-fx-background-color: rgb(255, 255, 255)");
                allNode.setStyle("-fx-border-color: transparent");
            } catch (Exception f) {

            }
        });

    }

    public static Integer verifyIntConvert(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException | NullPointerException nv) {
            // System.err.println("NumberFormat pointer exception while converting from String to int");
            return 0;
        }
        //System.err.println("Null pointer exception while converting from String to int");

    }

    public static boolean Exist(String query, DBConnect dbconnect) {

        Statement stmt = dbconnect.getStatement();
        ResultSet rs = null;
        String st = "";
        try {

            rs = dbconnect.getStatement().executeQuery(query);

            if (rs.next()) {
                String test = rs.getString(1);
                return !test.equals("0");
            }
        } catch (SQLException sqe) {
            String message = sqe.getMessage();
            Util.writeToErrorLog(message);

        }
        return false;

    }

    public static String addEscape(String st) {
        String r = "";
        if (st != null) {
            if (st.contains("'")) {
                r = st.replace("'", "'\'");
            } else if (st.contains("\"")) {
                r = st.replace("\"", "\"\"");
            } else {
                return st;
            }
        }
        return r;
    }

    public static void setAllFieldstoEditable(Object patient_TabbedPane) {

        ArrayList<Node> allNodes = getAllNodes((Parent) patient_TabbedPane);

        allNodes.stream().map((comp) -> {
            if (comp instanceof TextField) {
                TextField textField = (TextField) comp;
                textField.setEditable(true);
            } //
            return comp;
        }).map((comp) -> {
            if (comp instanceof TextArea) {
                TextArea textArea = (TextArea) comp;
                textArea.setEditable(true);
            } // 
            return comp;
        }).map((comp) -> {
            if (comp instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) comp;
                checkBox.setDisable(false);
            } //
            //
            return comp;
        }).filter((comp) -> (comp instanceof ComboBox)).map((comp) -> (ComboBox) comp).forEachOrdered((comboBox) -> {
            comboBox.setDisable(false);
        });
    }

    public static void setDisableTextArea(TabPane patient_TabbedPane, boolean b) {
        ArrayList<Node> allNodes = getAllNodes(patient_TabbedPane);

        allNodes.stream().filter((comp) -> (comp instanceof TextArea)).map((comp) -> (TextArea) comp).forEachOrdered((textArea) -> {
            textArea.setDisable(b);
        });
    }

    public static void setDisableTextFields(Object patient_TabbedPane, boolean b) {
        ArrayList<Node> allNodes = getAllNodes((Parent) patient_TabbedPane);

        allNodes.stream().filter((comp) -> (comp instanceof TextField)).map((comp) -> (TextField) comp).forEachOrdered((textField) -> {
            textField.setDisable(b);
        }); //
    }

    public static boolean validateAllFields(Object patient_TabbedPane) {

        ArrayList<Node> allNodes = getAllNodes((Parent) patient_TabbedPane);
        boolean fieldsOk = true;
        try {
            for (Node comp : allNodes) {
                if (comp == null) {
                    writeToErrorLog("component null in validatingFields");
                    break;
                }
                if (comp instanceof TextField) {

                    TextField textField = (TextField) comp;

                    if (textField.getText().isEmpty()) {
                        //  textField.setStyle("-fx-background-color: #f08080");
                        textField.setStyle("-fx-border-color: red");
                        fieldsOk = false;
                    }
                } // end of validating all text fields
                if (comp instanceof TextArea) {
                    try {
                        TextArea textArea = (TextArea) comp;
                        if (textArea.getText().isEmpty()) {
                            textArea.setStyle("-fx-border-color: red");
                            fieldsOk = false;
                        }
                    } catch (Exception e) {

                    }
                } // end of validating all text Areas
                if (comp instanceof ComboBox) {
                    ComboBox comboBox = (ComboBox) comp;
                    try {
                        if (comboBox.getValue().toString().length() == 0) {
                            comboBox.setStyle("-fx-border-color: red");
                            fieldsOk = false;
                        }
                    } catch (NullPointerException k) {
                        //    System.err.println("Null");
                        comboBox.setStyle("-fx-border-color: red");
                    }
                } // end of validating all combo Boxes
            }
            if (!fieldsOk) {

            }
        } catch (Exception ed) {
            //System.err.println("Error");
            System.err.println(ed.getMessage());
        }
        return fieldsOk;
    }

    public static Double parseDouble(String s) {

        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException nm) {
            return 0.0;
        }

    }

    public static void setDisableAll(Object pane, boolean b) {
        ArrayList<Node> allNodes = getAllNodes((Parent) pane);
        allNodes.stream().map((comp) -> {
            if (comp instanceof TextField) {
                TextField textField = (TextField) comp;
                textField.setDisable(b);
            } //
            return comp;
        }).map((comp) -> {
            if (comp instanceof TextArea) {
                TextArea textArea = (TextArea) comp;
                textArea.setDisable(b);
            } // 
            return comp;
        }).map((comp) -> {
            if (comp instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) comp;
                checkBox.setDisable(b);
            } //
            //
            return comp;
        }).filter((comp) -> (comp instanceof ComboBox)).map((comp) -> (ComboBox) comp).forEachOrdered((comboBox) -> {
            comboBox.setDisable(b);
        });
    }

    public static void setAllFieldstoReadOnly(Object pane) {
        ArrayList<Node> allNodes = getAllNodes((Parent) pane);
        // ObservableList<Node> n=pane.getChildren();
        allNodes.stream().map((comp) -> {
            if (comp instanceof TextField) {
                TextField textField = (TextField) comp;
                textField.setEditable(false);
            } //End of clearing all text fields
            return comp;
        }).map((comp) -> {
            if (comp instanceof TextArea) {
                TextArea textArea = (TextArea) comp;
                textArea.setEditable(false);
            } // end of clearing all text Areas
            return comp;
        }).map((comp) -> {
            if (comp instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) comp;
                checkBox.setDisable(true);
            } // end of clearing all check boxes
            // end of clearing all combo Boxes
            return comp;
        }).filter((comp) -> (comp instanceof ComboBox)).map((comp) -> (ComboBox) comp).forEachOrdered((comboBox) -> {
            //  comboBox.setEditable(false);
            comboBox.setDisable(true);
        });
    }

    public Util() {
    }

    public static void populateComboList(String property, ComboBox comboBox) {

        String[] etatCivil = property.split(",");
        comboBox.getItems().addAll(Arrays.asList(etatCivil));
        comboBox.setValue("");
    }

    public static boolean Delete(String query, DBConnect dbconnect) {
        if (dbconnect == null) {
            dbconnect = new DBConnect();
            dbconnect.run();

        }
        while (!dbconnect.getConnectionStatus()) {
            System.err.println("Connection error, Trying to reconnect");
            dbconnect.run();
            Util.writeToErrorLog("Connection error, Trying to reconnect");
        }

        boolean done = false;
        Statement stmt = dbconnect.getStatement();
        try {
            int rc = stmt.executeUpdate(query);
            done = true;
            if (Util.debugOn()) {
                writeToInfoLog("Info: executed (deleted): " + query + " Return Code is: " + rc);
            }
        } catch (SQLException e) {
            String message = e.getMessage();
            Util.writeToErrorLog(message);
        }

        return done;
    }
    //===========================================================================

    protected static Calendar generateCal() {
        Calendar calendar = Calendar.getInstance();
        return calendar;
    }

    //==========================================================================
    public static String getProperty(String string) {

        String separator = System.getProperty("file.separator");
        StringBuilder sb = new StringBuilder();
        sb.append(System.getenv("localappdata")).append(separator).append("Cabinet Medical").append(separator).append("config")
                .append(separator).append("medical.properties");

        try {
            Reader reader = new InputStreamReader(new FileInputStream(sb.toString()), "UTF-8");
            //Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
            try (BufferedReader fin = new BufferedReader(reader)) {
                properties = new Properties();

                properties.load(fin);
            }
        } catch (IOException io) {
        }
        return properties.getProperty(string);
    }

    public static String getProperty(String string, String location) {

        Properties p = null;

        try {
            Reader reader = new InputStreamReader(new FileInputStream(location), "UTF-8");
            //Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
            try (BufferedReader fin = new BufferedReader(reader)) {
                p = new Properties();

                p.load(fin);
            }
        } catch (IOException io) {
        }
        return p.getProperty(string);
    }
    //===========================================================================

    public static Properties getProperties(String location) {

        Properties p = null;

        try {
            Reader reader = new InputStreamReader(new FileInputStream(location), "UTF-8");
            //Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
            try (BufferedReader fin = new BufferedReader(reader)) {
                p = new Properties();

                p.load(fin);
            }
        } catch (IOException io) {
        }
        return p;
    }

    public static boolean debugOn() {
        String debugFlag = Util.getProperty("debug");
        return debugFlag.equals("true");
    }

    //==========================================================================
    public static void writeToInfoLog(String text) {
//        String infoLog = Util.getProperty("infoLog");
        String infoLog = Util.getProperty("statistic_root") + "\\logs.txt";
        PrintWriter out = null;
        BufferedWriter bufferedWriter;
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(infoLog, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            out = new PrintWriter(bufferedWriter);
            out.write(generateCal().getTime().toString() + ": " + text);
            out.println();
            out.flush();
        } catch (IOException e) {
            writeToErrorLog(e.toString());

        }

    }

    //==========================================================================
    public static void writeToErrorLog(String text) {
//        String errorLog = Util.getProperty("errorLog");
        String errorLog = Util.getProperty("statistic_root") + "\\error.txt";
        PrintWriter out = null;
        BufferedWriter bufferedWriter;
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(errorLog, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            out = new PrintWriter(bufferedWriter);
            out.write(generateCal().getTime().toString() + ": " + text);
            out.println();
            out.flush();
        } catch (IOException e) {

        }

    }

    //==========================================================================
    private static int createLog(String log) {
        FileOutputStream newFile;
        int rc = 0;
        final long LogSize = Long.parseLong(getProperty("LogSize"));

        try {
            File file = new File(log);
            if (file.exists()) {
                long currentSize = file.length();
                if (currentSize >= LogSize) {
                    File oldFile = new File(log + generateCal().getTimeInMillis());
                    file.renameTo(oldFile);
                    newFile = new FileOutputStream(file);
                    newFile.close();
                }
            } else {
                if (!file.exists()) {
                    newFile = new FileOutputStream(file);
                    newFile.close();
                }
            }
        } catch (IOException e) {
            rc = -1;
            writeToErrorLog(e.toString());
        }
        return rc;
    }

    public static void saveProperties(Properties p, String location) {

        FileWriter writer;
        try {
            writer = new FileWriter(location);
            p.store(writer, "Updated on " + LocalDate.now());
        } catch (IOException ex) {
            System.err.println("Error Saving ");
        }

    }

    //==========================================================================
    private static void initialize() {
        int infoLog_rc = createLog(getProperty("infoLog"));
        int errorLog_rc = createLog(getProperty("errorLog"));
    }

}
