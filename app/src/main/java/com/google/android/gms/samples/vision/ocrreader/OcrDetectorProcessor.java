/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gms.samples.vision.ocrreader;

import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.android.gms.samples.vision.ocrreader.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

/**
 * A very simple Processor which gets detected TextBlocks and adds them to the overlay
 * as OcrGraphics.
 */
public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> graphicOverlay;

    OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay) {
        graphicOverlay = ocrGraphicOverlay;
    }

    /**
     * Called by the detector to deliver detection results.
     * If your application called for it, this could be a place to check for
     * equivalent detections by tracking TextBlocks that are similar in location and content from
     * previous frames, or reduce noise by eliminating TextBlocks that have not persisted through
     * multiple detections.
     */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        graphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                Log.d("OcrDetectorProcessor", "Text detected! " + item.getValue());
                OcrGraphic graphic = new OcrGraphic(graphicOverlay, item);
                graphicOverlay.add(graphic);
            }
        }
    }
            //Не смог наладить проверку через arraylist//
    public boolean checkText(Detector.Detections<TextBlock> detections) {
        SparseArray<TextBlock> items = detections.getDetectedItems();
        SparseArray array = detections.getDetectedItems();
        array.put(0, "e103");
        array.put(1, "e123");
        array.put(2, "e216");
        array.put(3, "e217");
        Collections.sort((List<Comparable>) items);
        Collections.sort((List<Comparable>) array);
        boolean equalLists = items.size() == array.size() && ((List<Comparable>) items).containsAll((Collection<?>) array);
        Set<Integer> objectsIds = new HashSet<>();
        if (items == null && array == null)
            return false;
        if (items == null && array != null)
            return true;
        if (items != null && array == null)
            return true;
        int max = items.size() > array.size() ? items.size() : array.size();
        for (int i = 0; i < max; i++) {
            if (!items.equals(array)) {
                return true;
            }
        }
        return false;
    }


        /**
         * Frees the resources associated with this detection processor.
         */
        @Override
        public void release () {
            graphicOverlay.clear();
        }
    }

