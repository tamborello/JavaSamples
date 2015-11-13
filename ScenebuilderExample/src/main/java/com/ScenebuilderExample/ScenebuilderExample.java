/*
ScenebuilderExample

@author Frank Tamborello
frank.tamborello@cogscent.com
Availability: Public
License: Creative Commons CC-BY-SA 4.0
https://creativecommons.org/licenses/by-sa/4.0/legalcode

Description: Build a GUI application using Scenebuilder.
Oracle's Scenebuilder tutorial:
http://www.oracle.com/technetwork/java/javase/downloads/sb2download-2177776.html

Scenebuilder software:
http://gluonhq.com/scene-builder-installers-now-available/

Scenebuilder samples:
http://www.oracle.com/technetwork/java/javafxscenebuilder-1x-archive-2199384.html

Revision: 6

Revision History

Revision 1  2015.11.05
Inception


Revision 2  2015.11.05
Progress save for more interface elements added, tutorial page 5.


Revision 3  2015.11.05
Progress save for more interface elements added, tutorial page 8.


Revision 4  2015.11.07
Android compilation requires a top-level domain name, such as "com," be prepended to the project class name.
Bug: Does not compile and launch, throwing a java fxml load exception.

Revision 5  2015.11.10
Traced r4's compilation failure to a load exception failure due bad reference to class ScenebuilderExample in line 12 of ScenebuilderExample.fxml: the "com" name prefix was missing.
Project now compiles and launches the GUI application. 
Bugs: What I see in the Scenebuilder tool is not what I get in the application!
Missing elements: "Issue Tracking Lite" logo
Otherwise, everything appears as expected.

Revision 6  2015.11.11
Bug squashed: missing "Issue Tracking Lite" logo image. It turns out Scenebuilder needed that png to be in the same directory as the fxml file,
http://blog.professional-webworkx.de/javafx-create-fxml-in-scene-builder-and-add-image-to-imageview/
…but page 8 of Oracle's Scenebuilder tutorial didn't mention that!
http://docs.oracle.com/javase/8/scene-builder-2/get-started-tutorial/add-toolbar.htm

Note: When the application first draws the window, all the elements appear oversized. Then when I drag the application window to another display, everything gets redrawn to the smaller size I expect, and everything appears fine. 
*/

/*
 * Copyright (c) 2012, 2014, Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.ScenebuilderExample;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScenebuilderExample extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(ScenebuilderExample.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
/*            
*            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ScenebuilderExample.fxml"));
/*
/*
*        VBox page = (VBox) FXMLLoader.load(ScenebuilderExample.class.getResource("/com.ScenebuilderExample/ScenebuilderExample.fxml"));
*/
            primaryStage.setTitle("Scenebuilder Example");
            VBox page = (VBox) FXMLLoader.load(getClass().getClassLoader().getResource("ScenebuilderExample.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(ScenebuilderExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

