/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2007-2018 Broad Institute
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.igv.ui.panel;

import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.broad.igv.ui.panel.FrameManager;
import org.broad.igv.ui.panel.ReferenceFrame;
import org.igv.ui.JavaFXUIUtilities;
import org.igv.ui.Track;

// Intended as the rough equivalent of the DataPanelContainer class of the Swing UI.  Work in progress.
// Note: Not dealing with DnD yet.
public class DataPaneContainer extends BorderPane {

    private Track track;
    private HBox contentPane = new HBox();

    public DataPaneContainer(Track track) {
        this.track = track;
        JavaFXUIUtilities.bindWidthToContainer(this, contentPane);
        setCenter(contentPane);
        createDataPanes();
    }

    public void createDataPanes() {
        contentPane.getChildren().clear();

        for (ReferenceFrame f : FrameManager.getFrames()) {
            if (f.isVisible()) {
                DataPane dataPane = new DataPane(f, track, this);
                dataPane.backgroundProperty().bind(backgroundProperty());
                contentPane.getChildren().add(dataPane);
            }
        }
    }

    public DoubleProperty frameSpacingProperty() {
        return contentPane.spacingProperty();
    }
}
