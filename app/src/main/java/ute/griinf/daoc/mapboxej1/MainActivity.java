package ute.griinf.daoc.mapboxej1;

import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import android.os.Bundle;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//https://www.mapbox.com/

//Es necesario crear una cuenta gratuita: https://account.mapbox.com/auth/signup/
//y utilizar el token público: https://account.mapbox.com/access-tokens/
//El cual se debe poner en:
//    Mapbox.getInstance(context, token)

// Revisar build.gradle de módulo por repositorio y dependencia
// Revisar manifest por permisos
public class MainActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "AQUÍ VA EL TOKEN PÚBLICO !!!!");
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                final LocationComponent locationComponent = mapboxMap.getLocationComponent();
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        locationComponent.activateLocationComponent(
                                LocationComponentActivationOptions.builder(getApplicationContext(), style).build());
                        //Activa y permite que el marcador de posición se vea
                        locationComponent.setLocationComponentEnabled(true);
                        //Permite que la cámara siga la posición
                        locationComponent.setCameraMode(CameraMode.TRACKING);
                    }
                });
                //Zoom inicial y, si se usa target (está comentado), una posición inicial
                mapboxMap.setCameraPosition(
                    new CameraPosition.Builder()
                        //.target(new LatLng(-0.1817, -78.5077))
                        .zoom(15)
                        .build());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}