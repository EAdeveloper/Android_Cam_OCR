package com.eddeveloper.camera_ocr;

import android.hardware.Camera;
import android.view.SurfaceHolder;

/**
 * Created by Sunshine on 12/6/2016.
 */
public class confCamara {
    boolean encedido;
    Camera camara;
    SurfaceHolder surfaceHolder;

    Camera.AutoFocusCallback autoFocusCallback =new Camera.AutoFocusCallback(){
        @Override
        public void onAutoFocus(boolean success, Camera camera) {

        }
    };

    public boolean estaEncedido(){
        return encedido;
    }
    private confCamara(SurfaceHolder surfaceHolder){
        this.surfaceHolder=surfaceHolder;
    }

    static public confCamara nuevo(SurfaceHolder surfaceHolder){
        return new confCamara(surfaceHolder);
    }

    public void enfoque(){
        if(camara==null){
            return;
        }
        if(estaEncedido()){
            try{
                camara.autoFocus(autoFocusCallback);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void iniciar(){
        this.camara = getCamara();
        if(this.camara==null){
            return;
        }
        try{
            this.camara.setPreviewDisplay(this.surfaceHolder);
            this.camara.setDisplayOrientation(90);
            this.camara.startPreview();
            encedido=true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deterner(){
        if(camara!=null){
            camara.release();
            camara=null;
        }
        encedido=false;
    }

    public void takeShoot(Camera.ShutterCallback shutterCallback,Camera.PictureCallback rawPictureCallback,Camera.PictureCallback jpgPictureCallback){
        if(estaEncedido()){
            camara.takePicture(shutterCallback, rawPictureCallback, jpgPictureCallback);
        }
    }


    public static Camera getCamara(){
        try{
            return Camera.open();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
