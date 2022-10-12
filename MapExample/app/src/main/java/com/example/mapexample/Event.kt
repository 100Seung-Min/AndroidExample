package com.example.mapexample

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import java.util.*


val PERMISSIONS = arrayOf(
    Manifest.permission.ACCESS_FINE_LOCATION
)

fun checkPermissions(context: Context): Boolean {
    PERMISSIONS.forEach { permission ->
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            return false
        }
    }
    return true
}

fun getAddress(context: Context, latitude: Double, longitude: Double): String{
    val geocoder = Geocoder(context, Locale.KOREA)
    var nowAddr = "현재 위치를 확인 할 수 없습니다"
    try {
        val address = geocoder.getFromLocation(latitude,longitude,1)
        if(address != null && address.isNotEmpty()){
            nowAddr = address[0].getAddressLine(0).toString()
        }
    } catch (e: IOException){
        Toast.makeText(context, "주소를 가져올 수 없습니다", Toast.LENGTH_SHORT).show()
        e.printStackTrace()
    }
    return nowAddr
}

fun getPosition(context: Context, address: String): List<Address>? {
    val geocoder = Geocoder(context, Locale.KOREA)
    val list = geocoder.getFromLocationName(address, 100)
    if(list != null) {
        return if (list.size == 0) {
            null
        } else {
            list
        }
    }
    return null
}

@SuppressLint("MissingPermission")
fun getLocation(activity: FragmentActivity?): Location? {
    val locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
}