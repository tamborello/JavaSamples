/*
CC-BY-SA 2015 Frank Tamborello
This library is free software; you can redistribute it and/or modify it under 
the terms of Creative Commons Attribute-ShareAlike 4.0 International License:
http://creativecommons.org/licenses/by-sa/4.0/
This library is distributed in the hope that it will be useful,but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE.


Project Name: Draw a Scatterplot
Description: Creates a simple, editable, graphical data plotting application. 
This is a tutorial from these sites:
http://docs.oracle.com/javafx/2/charts/line-chart.htm
http://docs.oracle.com/javafx/2/ui_controls/table-view.htm
http://docs.oracle.com/javase/8/javase-clienttechnologies.htm

Revision 1  2015.10.19
Inception; draws some example data on a 2-axis line plot.

Revision 2  2015.10.19
1. Added the ReadFile class from Assignment 1
2. Changed the chart type from LineChart to ScatterChart, as well as changed 
some of the hard-coded details like axis labels.

Revision 3  2015.10.19
Hacked the yAxis to display invertedly.

Revision 4  2015.10.19
Textfields and an update button to change the axes' ranges from the GUI.

Revision 5  2015.10.20
Added a button to activate the Javafx file chooser: 
http://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm

Revision 6  2015.10.20
DrawScatterPlot actually captures the file from the FileChooser.

Revision 7  2015.10.20
Plots the data captured from the FileChooser, even with the inverted y-axis.
 */
package DrawScatterPlot;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.awt.Desktop;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
 
public class DrawScatterPlot extends Application {
    
    final HBox hb = new HBox();
    private Desktop desktop = Desktop.getDesktop();

 
    @Override public void start(Stage stage) {
        stage.setTitle("Well Inclination & Depth");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis("Inclination (Deg)", 0.0, 90.0, 10.0);
        xAxis.setSide(Side.TOP);
/* JavaFX is apparently doesn't support inverted axes, so hack around it:
        http://stackoverflow.com/questions/18026706/chart-with-inverted-y-axis
*/      
        final NumberAxis yAxis = new NumberAxis("Depth (ft)", -8000.0, 0.0, 500.0);
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis) {
        @Override
        public String toString(Number value) {
            // if value is 0 then don't print minus value, just print 0
            if (value.intValue() == 0) {
                return String.format("0");
            } else {
            // note we are printing minus value
            return String.format("%7.0f", -value.doubleValue());
            }
        }
    });
        //creating the chart
        final ScatterChart<Number,Number> scatterChart = 
                new ScatterChart<Number,Number>(xAxis,yAxis);
                
        //defining a series
        XYChart.Series series = new XYChart.Series();
        //populating the series with data
//        series.getData().add(new XYChart.Data(1, -1300));
        
        Scene scene  = new Scene(new Group());
        scatterChart.getData().add(series);
        
        final FileChooser fileChooser = new FileChooser();
        final Button openButton = new Button("Open Data...");
        
        final ReadFile rf;
        
        openButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                    ReadFile rf = new ReadFile(file);
                    try {
            String[] aryLines = rf.OpenFile();
            String[][] data = new String [aryLines.length-2] [3];
            
            int i;
            for (i=0; i < aryLines.length; i++) {
            if (i>1) {data[i-2] = aryLines[i].split("\t");}
                }
            
//            System.out.println(aryLines[0]);
//            System.out.println(String.format("%s    %s  %s", data[0][0], data[0][1], data[0][2]));
            
            for (i=0; i < data.length; i++) {
//                System.out.println(String.format("%s %s", data[i][1], data[i][0]));
            series.getData().add(new XYChart.Data(
                    Double.parseDouble(data[i][1]),
                    -1 * Double.parseDouble(data[i][0])));
            }
            
                    }
                    catch (IOException ioe) {
                        System.out.println(ioe.getMessage());
                    }
                    }
                    }
            });
       
        final TextField xLowerBound = new TextField();
        xLowerBound.setPromptText(String.format("%s", xAxis.getLowerBound()));
        xLowerBound.setMaxWidth(75.0);

        final TextField xUpperBound = new TextField();
        xUpperBound.setPromptText(String.format("%s", xAxis.getUpperBound()));
        xUpperBound.setMaxWidth(75.0);

        final TextField yLowerBound = new TextField();
        yLowerBound.setPromptText(String.format("%s", yAxis.getLowerBound()));
        yLowerBound.setMaxWidth(75.0);

        final TextField yUpperBound = new TextField();
        yUpperBound.setPromptText(String.format("%s", yAxis.getUpperBound()));
        yUpperBound.setMaxWidth(75.0);
        
        final Button updateAxes = new Button("Update Axes");
        updateAxes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                xAxis.setLowerBound(Double.parseDouble(xLowerBound.getText()));
                xAxis.setUpperBound(Double.parseDouble(xUpperBound.getText()));
                yAxis.setLowerBound(Double.parseDouble(yLowerBound.getText()));
                yAxis.setUpperBound(Double.parseDouble(yUpperBound.getText()));
            }
        });


        hb.getChildren().addAll(openButton, xLowerBound, xUpperBound, 
                yLowerBound, yUpperBound, updateAxes);
        hb.setSpacing(3);
 

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(scatterChart, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(DrawScatterPlot.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
}