package com.example.map

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.strictmode.CleartextNetworkViolation
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.model.*
import com.google.android.material.textfield.TextInputEditText
import java.io.IOException
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var Lat = 18.894015631407996
    var Lng = 99.0108942613
    private val LOCATION_REQUEST_CODE = 200


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        var status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this@MapsActivity)
        if (status == ConnectionResult.SUCCESS){
            if (ActivityCompat.checkSelfPermission(this@MapsActivity, android.Manifest.permission.ACCESS_FINE_LOCATION)
            !=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this@MapsActivity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),LOCATION_REQUEST_CODE)
            }
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        var cmdClear = findViewById<Button>(R.id.cmdClear)
        cmdClear.setOnClickListener{
            mMap.clear()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val jj = LatLng(Lat, Lng)
        if (ActivityCompat.checkSelfPermission(this@MapsActivity,android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this@MapsActivity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),LOCATION_REQUEST_CODE)
        }else{
            mMap.isMyLocationEnabled = true
            mMap.uiSettings.isZoomControlsEnabled = true
            mMap.setMapType(GoogleMap.MAP_TYPE_NONE)
        }

        mMap.addMarker(MarkerOptions().position(jj).title("มหาวิทยาลัยแม่โจ้"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jj))
        mMap.setOnMapClickListener { latLng ->
            val strAddress = ("Lat : " + latLng.latitude.toString() + " " + "Lng : " + latLng.longitude.toString())
            val m = MarkerOptions()
            m.position(latLng)
            m.title(strAddress)
            mMap.addMarker(m)
        }
    }

}