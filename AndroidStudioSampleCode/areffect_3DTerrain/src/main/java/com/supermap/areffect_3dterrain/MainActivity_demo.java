package com.supermap.areffect_3dterrain;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.supermap.ar.Point3D;
import com.supermap.ar.areffect.ARAnimationGroup;
import com.supermap.ar.areffect.ARAnimationManager;
import com.supermap.ar.areffect.ARAnimationParameter;
import com.supermap.ar.areffect.ARAnimationRepeatMode;
import com.supermap.ar.areffect.ARAnimationRotation;
import com.supermap.ar.areffect.AREffectElement;
import com.supermap.ar.areffect.AREffectView;
import com.supermap.ar.areffect.ARViewElement;
import com.supermap.ar.areffect.Vector;


import pub.devrel.easypermissions.EasyPermissions;

/**
 * <p>
 * Title:ARɳ�̵�ʾ������
 * </p>
 *
 * <p>
 * Description:
 * ============================================================================>
 * ------------------------------��Ȩ����----------------------------
 * ���ļ�ΪSuperMap iMobile for Android ��ʾ������
 * ��Ȩ���У�������ͼ����ɷ����޹�˾
 * ----------------------------------------------------------------
 * ---------------------SuperMap iMobile for Android ʾ������˵��------------------------
 *
 * 1��������飺ʾ�������AR�����м���ģ�ͼ���ͼ����ARɳ��
 * 2���ؼ�����/��Ա:
 *      AREffectView
 * 		AREffectElement
 * 	    ARViewEffectElement
 * 3��ʹ�ò��裺
 *   (1)�����ͬ��ť�����ز�ͬ��ģ�ͻ���ͼ
 *   (2)�������ť��ģ��������תһ���Ƕ�
 * ------------------------------------------------------------------------------
 * ============================================================================>
 * </p>
 *
 * <p>
 * Company: ������ͼ����ɷ����޹�˾
 * </p>
 *
 */
public class MainActivity_demo extends AppCompatActivity {
    float rotationAngle = 0f;//��ת�Ƕ�
    Vector axisZ = new Vector(0,0,1);//Z��
    float heiScalse = 0.6f;//ģ�͸߶ȱ�
    ARAnimationGroup animationGroup;//������
    private AREffectView arFragment;//��Ч�ؼ�
    AREffectElement parentElement;//���ڵ�

    AREffectElement terrainElement;//����
    AREffectElement terrainModelElement;
    AREffectElement routeElement;//·��

    private AREffectElement ticDoorElement;//���ż���Ʊ��01
    private AREffectElement ticDoorElement02;//���ż���Ʊ��02

    ARViewElement markerElement_01;//���-�ı�
    ARViewElement markerElement_00;//���-�ı�

    private AREffectElement vistorCenterElement;//�ο�����
    private ARViewElement vistorCenterElement_Marker;//�ο����ı��01
    private ARViewElement vistorCenterElement_Marker02;//�ο����ı��02

    private AREffectElement vistorCenterElement_03;
    private ARViewElement markerElement_02;//���-�ı�
    private ARViewElement markerElement_03;
    private ARViewElement markerElement_04;
    private ARViewElement markerElement_05;

    private AREffectElement arbor01;
    private AREffectElement arbor02;
    private AREffectElement wharfElement;

    private ARViewElement arbor02_marker;

    protected String[] needPermissions = {
            Manifest.permission.CAMERA,
    };
    private AREffectElement routeElement02;
    private AREffectElement wharfElement02;
    private AREffectElement shipElement;
    private ARViewElement wharfElement02_marker;
    private AREffectElement route_marker_01;
    private AREffectElement route_marker_02;
    private AREffectElement route_marker_03;
    private ARViewElement route_marker_01_text;
    private ARViewElement route_marker_02_text;
    private ARViewElement route_marker_03_text;
    private AREffectElement route_marker_line;
    private ARViewElement route_marker_04_text;

    @Override
    protected void onPause() {
        super.onPause();
        arFragment.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        arFragment.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions();

        setContentView(R.layout.activity_main_test);
        arFragment = findViewById(R.id.ar_effect);

        int width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindow().getWindowManager().getDefaultDisplay().getHeight();
        if (width > height){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        initMainModel();//����ģ��

        //<editor-fold>���ð�ť��
        findViewById(R.id.leftBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ģ����ʱ����ת
                rotationAngle += 15;
                terrainElement.setRotationAngle(axisZ, rotationAngle);
            }
        });
        findViewById(R.id.rightBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ģ��˳ʱ����ת
                rotationAngle -= 15;
                terrainElement.setRotationAngle(axisZ, rotationAngle);
            }
        });

        findViewById(R.id.btn01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //��ʾ�����أ�����ģ�ͣ�
                terrainElement.setVisiblity(true);//����
                ticDoorElement.setVisiblity(true);//����
                ticDoorElement02.setVisiblity(true);//����
                markerElement_00.setVisiblity(true);//���ű��
                markerElement_03.setVisiblity(true);//���ű��
                vistorCenterElement.setVisiblity(true);
                vistorCenterElement_03.setVisiblity(true);
            }
        });

        findViewById(R.id.btn02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //��ʾ�����أ��ڲ�����
                arbor02.setVisiblity(true);//ͤ��01
                arbor01.setVisiblity(true);//ͤ��02

                wharfElement.setVisiblity(true);//��ͷ

                wharfElement02.setVisiblity(true);
                shipElement.setVisiblity(true);
            }
        });

        findViewById(R.id.btn03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //��ʾ�����أ�ͼ��
                vistorCenterElement_Marker.setVisiblity(true);
                vistorCenterElement_Marker02.setVisiblity(true);
                markerElement_05.setVisiblity(true);//ˮ����
                markerElement_02.setVisiblity(true);//�ο����ı��
                markerElement_01.setVisiblity(true);//ͤ��01���
                markerElement_04.setVisiblity(true);//���±��
                arbor02_marker.setVisiblity(true);
                wharfElement02_marker.setVisiblity(true);

                route_marker_01_text.setVisiblity(true);
                route_marker_02_text.setVisiblity(true);
                route_marker_03_text.setVisiblity(true);
                route_marker_04_text.setVisiblity(true);
                route_marker_line.setVisiblity(true);

            }
        });

        findViewById(R.id.btn04).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //��ʾ�����أ�·��
                routeElement.setVisiblity(true);
                routeElement02.setVisiblity(true);
                route_marker_01.setVisiblity(true);
                route_marker_02.setVisiblity(true);
                route_marker_03.setVisiblity(true);
            }
        });
        //</editor-fold>

        /**���߳�->���Ŷ���*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message=handler.obtainMessage();
                message.what=LOAD_MODEL;
                handler.sendMessage(message);
            }
        }).start();
    }

    /**
     * ���Ȩ��
     * return true:�Ѿ���ȡȨ��
     * return false: δ��ȡȨ�ޣ���������Ȩ��
     */
    public boolean checkPermissions(String[] permissions) {
        return EasyPermissions.hasPermissions(this, permissions);
    }

    /**
     * ����Ȩ��
     */
    private void requestPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        if (!checkPermissions(needPermissions)) {
            EasyPermissions.requestPermissions(
                    this,
                    "Ϊ��Ӧ�õ�����ʹ�ã�����������Ȩ�ޡ�",
                    0,
                    Manifest.permission.CAMERA);
            //û����Ȩ����д����Ȩ�޴���
        } else {
            //�Ѿ���Ȩ��ִ�в�������
        }
    }


    //<editor-fold>//���õ��κͿؼ�
    private void initMainModel() {
        //����λ��,���ø��ڵ�
        parentElement = new AREffectElement(getApplicationContext());
        parentElement.setParentNode(arFragment);
        parentElement.setPosition(new Point3D(0,0,0));

        //���νڵ�
        terrainElement = new AREffectElement(getApplicationContext());
        terrainElement.setParentNode(parentElement);
        terrainElement.setRelativePosition(new Point3D(0,2.5f,-1.5f));
        terrainElement.setScaleFactor(new float[]{1, 1, heiScalse});
        terrainElement.setVisiblity(false);

        //����ģ��
        terrainModelElement = new AREffectElement(getApplicationContext());
        terrainModelElement.setParentNode(terrainElement);
        terrainModelElement.loadModel(R.raw.terrain2gltf03);
//        terrainModelElement.setScaleFactor(new float[]{0.955f,0.955f,0.955f*heiScalse});//ģ�ͱ���
        terrainModelElement.setRotationAngle(axisZ,180);

        routeElement02 = new AREffectElement(getApplicationContext());
        routeElement02.setParentNode(terrainElement);
        routeElement02.setRelativePosition(new Point3D(-0.02f,2.0f,0.4f));
        routeElement02.setScaleFactor(new float[]{0.365f, 0.365f, heiScalse*0.365f});
        routeElement02.loadModel(R.raw.road05);
        routeElement02.setVisiblity(false);

        //����ͤ��
        arbor02 = new AREffectElement(getApplicationContext());
        arbor02.setParentNode(terrainElement);
        arbor02.setRelativePosition(new Point3D(-1.1f,1.1f,0.9f));
        arbor02.setScaleFactor(new float[]{0.165f, 0.165f, 0.165f});
        arbor02.setRotationAngle(axisZ,-90);
        arbor02.loadModel(R.raw.lt02arbor);
        arbor02.setVisiblity(false);

        //��ͷ
        wharfElement = new AREffectElement(getApplicationContext());
        wharfElement.setParentNode(terrainElement);
        wharfElement.setRelativePosition(new Point3D(-0.1f,-0.1f,0.3f));
        wharfElement.setScaleFactor(new float[]{0.065f, 0.065f, 0.065f});
        wharfElement.setRotationAngle(axisZ,-135);
        wharfElement.loadModel(R.raw.wharf);
        wharfElement.setVisiblity(false);

        //ͤ�ӱ��-����ͤ
        arbor02_marker = new ARViewElement(getApplicationContext());
        arbor02_marker.setParentNode(terrainElement);
        arbor02_marker.loadModel(R.layout.marker06);
        arbor02_marker.setRelativePosition(new Point3D(-0.3f,-0.3f,0.9f));
        arbor02_marker.setScaleFactor(new float[]{0.8f,0.8f,0.8f});
        arbor02_marker.setVisiblity(false);

        //�Ҳ���ͷ02
        wharfElement02 = new AREffectElement(getApplicationContext());
        wharfElement02.setParentNode(terrainElement);
        wharfElement02.setRelativePosition(new Point3D(1.45F,-0.45F,0.3f));
        wharfElement02.setScaleFactor(new float[]{0.08F, 0.08F, 0.08F});
        wharfElement02.setRotationAngle(axisZ,-30);
        wharfElement02.loadModel(R.raw.wharf);
        wharfElement02.setVisiblity(false);

        shipElement = new AREffectElement(getApplicationContext());
        shipElement.setParentNode(wharfElement02);
        shipElement.setRelativePosition(new Point3D(0,0,0));
        shipElement.setRelativePosition(new Point3D(1.35F,-1.6F,0.4f));
        shipElement.setScaleFactor(new float[]{0.065F, 0.065F, 0.065F});
        shipElement.loadModel(R.raw.ship);
        shipElement.setVisiblity(false);

        //�����
        wharfElement02_marker = new ARViewElement(getApplicationContext());
        wharfElement02_marker.setParentNode(wharfElement02);
        wharfElement02_marker.setRelativePosition(new Point3D(1.3f,-0.75f,2.15f));
        wharfElement02_marker.setScaleFactor(new float[]{0.8f,0.8f,0.8f});
        wharfElement02_marker.loadModel(R.layout.marker07);
        wharfElement02_marker.setVisiblity(false);

        //����ͤ
        arbor01 = new AREffectElement(getApplicationContext());
        arbor01.setParentNode(wharfElement);
        arbor01.setRelativePosition(new Point3D(2.4f,-1.8f,0.9f));
        arbor01.setScaleFactor(new float[]{0.2f, 0.2f, 0.2f});
        arbor01.setRotationAngle(axisZ,135);
        arbor01.loadModel(R.raw.lt01arbor);
        arbor01.setVisiblity(false);

        //<editor-fold>·����ָʾ��ͷ
        routeElement = new AREffectElement(getApplicationContext());
        routeElement.setParentNode(terrainElement);//·�ߵ����ڵ����ϣ�ʹ�õ��������λ�õĸ��ڵ�
        routeElement.setRelativePosition(new Point3D(-1.07f,0,0.18f));
        routeElement.loadModel(R.raw.road03);
        routeElement.setScaleFactor(new float[]{0.5f, 0.5f, 0.5f*heiScalse});
        routeElement.setVisiblity(false);

        route_marker_01 = new AREffectElement(getApplicationContext());
        route_marker_01.setParentNode(routeElement);//500m
        route_marker_01.setRelativePosition(new Point3D(1.3f,-1.55f,0.7f));
        route_marker_01.setScaleFactor(new float[]{0.01f, 0.01f, 0.01f});
        route_marker_01.loadModel(R.raw.blue);
        route_marker_01.setVisiblity(false);
        route_marker_01_text = new ARViewElement(getApplicationContext());
        route_marker_01_text.setParentNode(routeElement);
        route_marker_01_text.setScaleFactor(new float[]{0.55f,0.55f,0.55f});
        route_marker_01_text.setRelativePosition(new Point3D(1.4f,-1.50f,0.9f));
        route_marker_01_text.loadModel(R.layout.routemarker01);
        route_marker_01_text.setRotationAngle(axisZ,5);
        route_marker_01_text.setVisiblity(false);

        route_marker_02 = new AREffectElement(getApplicationContext());
        route_marker_02.setParentNode(routeElement);//1km
        route_marker_02.setRelativePosition(new Point3D(0.55f,-0.9f,0.65f));
        route_marker_02.setScaleFactor(new float[]{0.01f, 0.01f, 0.01f});
        route_marker_02.loadModel(R.raw.blue);
        route_marker_02.setVisiblity(false);

        route_marker_02_text = new ARViewElement(getApplicationContext());
        route_marker_02_text.setParentNode(routeElement);
        route_marker_02_text.setScaleFactor(new float[]{0.55f,0.55f,0.55f});
        route_marker_02_text.setRelativePosition(new Point3D(0.55f,-0.9f,0.85f));
        route_marker_02_text.loadModel(R.layout.routemarker02);
        route_marker_02_text.setRotationAngle(axisZ,10);
        route_marker_02_text.setVisiblity(false);

        route_marker_03 = new AREffectElement(getApplicationContext());
        route_marker_03.setParentNode(routeElement);//1km
        route_marker_03.setRelativePosition(new Point3D(-0.735f,1.0f,0.95f));
        route_marker_03.setScaleFactor(new float[]{0.01f, 0.01f, 0.01f});
        route_marker_03.loadModel(R.raw.blue);
        route_marker_03.setVisiblity(false);

        route_marker_03_text = new ARViewElement(getApplicationContext());
        route_marker_03_text.setParentNode(routeElement);
        route_marker_03_text.setScaleFactor(new float[]{0.55f,0.55f,0.55f});
        route_marker_03_text.setRelativePosition(new Point3D(-0.735f,1.0f,1.15f));
        route_marker_03_text.loadModel(R.layout.routemarker03);
        route_marker_03_text.setRotationAngle(axisZ,15);
        route_marker_03_text.setVisiblity(false);

        route_marker_line = new AREffectElement(getApplicationContext());
        route_marker_line.setParentNode(routeElement);//��԰·��ָʾ
        route_marker_line.setRelativePosition(new Point3D(0.8f,-1.35f,-1.2f));
        route_marker_line.setScaleFactor(new float[]{0.365f, 0.365f, 0.365f*heiScalse});
        route_marker_line.loadModel(R.raw.line);
        route_marker_line.setRotationAngle(axisZ,185);
        route_marker_line.setVisiblity(false);

        route_marker_04_text = new ARViewElement(getApplicationContext());
        route_marker_04_text = new ARViewElement(getApplicationContext());
        route_marker_04_text.setParentNode(route_marker_line);
        route_marker_04_text.setScaleFactor(new float[]{0.8f,0.8f,0.8f});
        route_marker_04_text.setRelativePosition(new Point3D(-0.8f,-0.55f,2.95f));
        route_marker_04_text.loadModel(R.layout.routemarker04);
        route_marker_04_text.setRotationAngle(axisZ,12);
        route_marker_04_text.setVisiblity(false);

        //</editor-fold>

        //<editor-fold>˫��
        //
        ticDoorElement = new AREffectElement(getApplicationContext());
        ticDoorElement.loadModel(R.raw.ticdoor);
        ticDoorElement.setParentNode(terrainElement);
        ticDoorElement.setRelativePosition(new Point3D(-0.15f,-1.2f,0.45f));
        ticDoorElement.setScaleFactor(new float[]{0.08f, 0.08f, 0.08f});
        ticDoorElement.setRotationAngle(new Vector(0,0,1),195);
        ticDoorElement.setVisiblity(false);

        //�ο�����
        vistorCenterElement = new AREffectElement(getApplicationContext());
        vistorCenterElement.loadModel(R.raw.bambohouse);//����
        vistorCenterElement.setParentNode(terrainElement);
        vistorCenterElement.setRelativePosition(new Point3D(1.2f,1.2f,0.55f));
        vistorCenterElement.setRotationAngle(new Vector(0,0,1),30);
        vistorCenterElement.setScaleFactor(new float[]{2.0f, 2.0f, 2.0f});
        vistorCenterElement.setRotationAngle(new Vector(0,0,1),150);
        vistorCenterElement.setVisiblity(false);

        vistorCenterElement_Marker = new ARViewElement(getApplicationContext());
        vistorCenterElement_Marker.setParentNode(vistorCenterElement);//���ݱ�ǣ�����ڷ��ݵ�λ��
        vistorCenterElement_Marker.setRelativePosition(new Point3D(0,0,0.1f));
        vistorCenterElement_Marker.setScaleFactor(new float[]{0.6f, 0.6f, 0.6f});
        vistorCenterElement_Marker.loadModel(R.layout.imgview);
        vistorCenterElement_Marker.setVisiblity(false);

        vistorCenterElement_03 = new AREffectElement(getApplicationContext());//����02
        vistorCenterElement_03.loadModel(R.raw.bambohouse);
        vistorCenterElement_03.setParentNode(vistorCenterElement);//����ڷ���
        vistorCenterElement_03.setRelativePosition(new Point3D(0.1f,0,0));
        vistorCenterElement_03.setScaleFactor(new float[]{3, 3, 2.4f});
        vistorCenterElement_03.setRotationAngle(new Vector(0,0,1),60);
        vistorCenterElement_03.setVisiblity(false);

        vistorCenterElement_Marker02 = new ARViewElement(getApplicationContext());
        vistorCenterElement_Marker02.loadModel(R.layout.imgview02);
        vistorCenterElement_Marker02.setParentNode(vistorCenterElement_Marker);
        vistorCenterElement_Marker02.setRelativePosition(new Point3D(0,0,0.1f));
        vistorCenterElement_Marker02.setRotationAngle(axisZ,90);
        vistorCenterElement_Marker02.setScaleFactor(new float[]{1.2f, 1.2f, 1.2f});
        vistorCenterElement_Marker02.setVisiblity(false);
        //</editor-fold>

        //<editor-fold>���������
        //�ҷ�Ӫ�ر��//���ű��
        markerElement_00 = new ARViewElement(getApplicationContext());
        markerElement_00.loadModel(R.layout.marker00);
        markerElement_00.setParentNode(ticDoorElement);
        markerElement_00.setRelativePosition(new Point3D(0.4f,0,3f));
        markerElement_00.setScaleFactor(new float[]{0.6f, 0.6f, 0.6f});
//        markerElement_00.setRotationAngle(axisZ,0);
        markerElement_00.setVisiblity(false);

        //1����ر��//����ͤ
        markerElement_01 = new ARViewElement(getApplicationContext());
        markerElement_01.loadModel(R.layout.marker01);
        markerElement_01.setParentNode(terrainElement);
        markerElement_01.setRelativePosition(new Point3D(-1.0f,1.1f,1.15f));
        markerElement_01.setScaleFactor(new float[]{1.0f, 1.0f, 1.0f});
        markerElement_01.setRotationAngle(axisZ,-10);
        markerElement_01.setVisiblity(false);


        //�з�Ӫ�ر�ǣ�����Ϊ���ο����ģ�
        markerElement_02 = new ARViewElement(getApplicationContext());
        markerElement_02.loadModel(R.layout.marker02);
        markerElement_02.setParentNode(vistorCenterElement_03);
        markerElement_02.setRelativePosition(new Point3D(0.05f,0.02f,0.062f));
        markerElement_02.setScaleFactor(new float[]{1.0f, 1.0f, 1.0f});
        markerElement_02.setRotationAngle(axisZ,-30);
        markerElement_02.setVisiblity(false);

        markerElement_03 = new ARViewElement(getApplicationContext());
        markerElement_03.loadModel(R.layout.marker03);//2nd���//����
        markerElement_03.setParentNode(terrainElement);
        markerElement_03.setScaleFactor(new float[]{1.365f, 1.365f, 1.365f});
        markerElement_03.setRelativePosition(new Point3D(0,2.4f,0.8f));
        markerElement_03.setVisiblity(false);

        ticDoorElement02 = new AREffectElement(getApplicationContext());
        ticDoorElement02.loadModel(R.raw.ticdoor);//�����Լ���Ʊ��
        ticDoorElement02.setParentNode(markerElement_03);
        ticDoorElement02.setScaleFactor(new float[]{0.08f, 0.08f, 0.08f});
        ticDoorElement02.setRelativePosition(new Point3D(-0.08f,-0.18f,-0.15f));
        ticDoorElement02.setVisiblity(false);

        markerElement_04 = new ARViewElement(getApplicationContext());
        markerElement_04.loadModel(R.layout.marker04);//������
        markerElement_04.setParentNode(terrainElement);
        markerElement_04.setScaleFactor(new float[]{1.365f, 1.365f, 1.365f});
        markerElement_04.setRelativePosition(new Point3D(-2.5f,2.5f,0.6f));
        markerElement_04.setRotationAngle(axisZ,30);
        markerElement_04.setVisiblity(false);

        markerElement_05 = new ARViewElement(getApplicationContext());
        markerElement_05.loadModel(R.layout.marker05);//ˮ���ע
        markerElement_05.setParentNode(terrainElement);
        markerElement_05.setScaleFactor(new float[]{0.8f, 0.8f, 0.8f});
        markerElement_05.setRelativePosition(new Point3D(-1.65f,-0.53f,0.45f));
        markerElement_05.setRotationAngle(axisZ,30);
        markerElement_05.setVisiblity(false);

        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold>����
    private static final int LOAD_MODEL=10001;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == LOAD_MODEL) {
                //�����-��ת
                ARAnimationParameter animationParameter = new ARAnimationParameter();//��ת����
                animationParameter.setRepeatMode(ARAnimationRepeatMode.REVERSE);//��ת������
                animationParameter.setDuration(5000L);//����

                ARAnimationRotation vistorCenterElement_Marker_Animation = new ARAnimationRotation(vistorCenterElement_Marker);
                vistorCenterElement_Marker_Animation.creatAnimation(animationParameter);//��������

                ARAnimationRotation route_marker_01_Animation = new ARAnimationRotation(route_marker_01);
                route_marker_01_Animation.creatAnimation(animationParameter);//��������

                ARAnimationRotation route_marker_02_Animation = new ARAnimationRotation(route_marker_02);
                route_marker_02_Animation.creatAnimation(animationParameter);//��������

                ARAnimationRotation route_marker_03_Animation = new ARAnimationRotation(route_marker_03);
                route_marker_03_Animation.creatAnimation(animationParameter);//��������

                animationGroup = ARAnimationManager.getInstance().addAnimationGroup("test_animation");//��ӡ�test_animation��������

                //������������Ӷ���
                animationGroup.addAnimation(vistorCenterElement_Marker_Animation);
                animationGroup.addAnimation(route_marker_01_Animation);
                animationGroup.addAnimation(route_marker_02_Animation);
                animationGroup.addAnimation(route_marker_03_Animation);

                /**--------�������ж���--------**/
                //�������ж���
                ARAnimationManager.getInstance().playAll();
            }
        }
    };

    //</editor-fold>
}
