package com.medprimetech.digitalmicroscopegetscale;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;


import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class GetScale {




    //postion of the scale in the image
    boolean isScaletop = true;


    // is the bar on top of the text
    boolean isLineBarOnTop = true;

    boolean isScaleBlack = true;
    String measure_text = "";

    Context c;

    public GetScale(Context context,boolean isScaletop,boolean isLineBarOnTop,boolean isScaleBlack) {

        isScaletop = isScaletop;
        isLineBarOnTop = isLineBarOnTop;
        isScaleBlack = isScaleBlack;
        c = context;
    }


    public void FindScale(Bitmap image){

        TextRecognizer recog = new TextRecognizer.Builder(c).build();
        int validScaleNumber = 0;




        if(recog.isOperational()){
            Frame frame = new Frame.Builder().setBitmap(image).build();
            SparseArray<TextBlock> items = recog.detect(frame);


            int vectorH = 0;

            if(isScaletop){
                vectorH = image.getHeight();
            }

            for(int ii = 0;ii< items.size();ii++){

                if(isScaletop){
                    if(items.valueAt(ii).getBoundingBox().top<vectorH){
                        vectorH = items.valueAt(ii).getBoundingBox().top;
                        validScaleNumber = ii;
                    }
                }else{
                    if(items.valueAt(ii).getBoundingBox().top>vectorH){
                        vectorH = items.valueAt(ii).getBoundingBox().top;
                        validScaleNumber = ii;
                    }
                }
            }


            measure_text = items.valueAt(validScaleNumber).getValue();

            // the text and the location has been finilised and now the search or the scale bar

            if(isLineBarOnTop){

                // find the zero

            }else{

            }




        }


    }


}
