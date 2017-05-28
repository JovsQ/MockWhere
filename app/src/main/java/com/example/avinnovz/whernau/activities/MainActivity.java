package com.example.avinnovz.whernau.activities;

import android.os.Bundle;
import android.widget.Button;

import com.example.avinnovz.whernau.R;
import com.example.avinnovz.whernau.base.BaseActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements OnMapReadyCallback{

    @BindView(R.id.btn_change_marker)
    Button btn_change_marker;

    private GoogleMap map;
    private Marker marker;
    private boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initMap();
    }

    private void initMap() {
        final MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (map == null) {
            map = googleMap;
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map.getUiSettings().setScrollGesturesEnabled(true);
            map.getUiSettings().setZoomGesturesEnabled(true);
            LatLng latLng = new LatLng(14.695179, 121.117852);
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(latLng);
            marker = map.addMarker(markerOptions);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
        }
    }

    @OnClick(R.id.btn_change_marker)
    public void changeMarkerLocation() {
        LatLng latLng = isFirst ? new LatLng(14.695179, 121.117852) :
                new LatLng(14.7011, 120.9830);
        isFirst = !isFirst;
        marker.setPosition(latLng);
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
