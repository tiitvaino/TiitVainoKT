package oop;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleAction;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;


import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class Valuutakalkulaator extends Application {


    public Map<String, BigDecimal> arvuta() throws IOException {
        String fail = "exchange-rates.txt";
        Path failiAaderess = Path.of(fail);
        Map<String, BigDecimal> eur2x;
        Scanner sc = new Scanner(failiAaderess);
        eur2x = new TreeMap<>();
        String[] rida;
        while (sc.hasNextLine()) {
            rida = sc.nextLine().split(" ");
            if (rida[0].length() != 3 || rida.length != 2) continue;
            eur2x.put(rida[0], new BigDecimal(rida[1]));
        }
        return eur2x;
    }


    @Override
    public void start(Stage peaLava) throws Exception {
        Map<String, BigDecimal> andmed = arvuta();
        int height = 120;
        int width = 400;

        GridPane gridPane = new GridPane();
       // gridPane.setGridLinesVisible(true);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Teeme logo must ruut, sinine ring, kollane euro.
        Canvas lõuend = new Canvas(100, 100);
        GraphicsContext gc = lõuend.getGraphicsContext2D();
        gc.fillRect(0,0,100,100);
        gc.setFill(Color.BLUE);
        gc.fillOval(0,0,100,100);
        gc.setStroke(Color.RED);
        gc.strokeRect(0,0,100,100);
        gc.setFont(new Font("Verdana",100));
        gc.setFill(Color.YELLOW);
        gc.fillText("€",0,100,100);


        //Lisame sildi "EUR".
        Label eur = new Label("EUR");
        eur.setAlignment(Pos.CENTER);
        eur.setMinWidth(70);
        //Lisame EUR sildi taha euro väärtuse, alg väärtus 0.
        TextField startValue = new TextField("0");
        startValue.setAlignment(Pos.CENTER);
        startValue.setMaxWidth(200);

        //Lisame muutuja, mida hakkame muutma vastavalt EUR väärtusele ja valitud valuutale
        final String[] endValueText = new String[1];

        //Lisame drop menu'sse failis leitud valuutad.
        ComboBox chooseCurrency = new ComboBox();
        for (String valuutad :
                andmed.keySet()) {
            chooseCurrency.getItems().add(valuutad);
        }

        //Paneme drop down menu alguses nähtavaks elemendiks, esimese elemendi nimekirjast.
        chooseCurrency.setValue(chooseCurrency.getItems().get(0));
        chooseCurrency.setMaxWidth(70);
        Label endValue = new Label("0");
        //Leiame valitud valuuta suhte.
        Object endC = chooseCurrency.getValue();


        //Kui euro väärtust muudetakse, siis muudame ka valitud valuuta väärtust.
        startValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) endValue.setText("");
                else if (!newValue.matches("\\d{0,1000}([\\.]\\d{0,1000})?")) endValueText[0] = "Only numbers and dot";
                else endValueText[0] = andmed.get(endC).multiply(new BigDecimal(newValue)).toString();
                endValue.setText(endValueText[0]);
            }
        });

        //Kui valitakse uus valuuta, siis muudame vastavalt sellele ka väärtust.
        chooseCurrency.addEventHandler(ActionEvent.ACTION, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                 String valitud = chooseCurrency.getSelectionModel().getSelectedItem().toString();
                endValueText[0] = andmed.get(valitud).multiply(new BigDecimal(startValue.getText())).toString();
                endValue.setText(endValueText[0]);
            }
        });

        //Label endValue = new Label(endValueText[0]);
        endValue.setMinWidth(200);
        endValue.setAlignment(Pos.CENTER);

        //Lisame Clear nuppu, mis 0 kõik väljad.
        Button clear = new Button("Clear fields");
        clear.setMinWidth(100);
        clear.setOnMouseClicked(event -> startValue.setText("0"));



        BorderPane borderPane = new BorderPane();
        //Lisame kõik eelenvalt tehtud elemendid gridBanesse.
        //gridPane.add(lõuend, 0, 2);
        gridPane.add(eur, 0, 0);
        gridPane.add(startValue, 1, 0);
        gridPane.add(chooseCurrency, 0, 1);
        gridPane.add(endValue, 1, 1);
        gridPane.add(clear, 1, 2);

        borderPane.setRight(gridPane);
        borderPane.setLeft(lõuend);

        Scene stseen1 = new Scene(borderPane, width, height, Color.SNOW);
        peaLava.setScene(stseen1);
        peaLava.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
