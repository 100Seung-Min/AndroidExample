package com.example.nfcexample

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.nfcexample.databinding.ActivityMainBinding
import java.lang.StringBuilder
import java.util.*

class MainActivity : AppCompatActivity() {
    private var TAG: String = "안녕"
    private lateinit var nfcPendingIntent: PendingIntent
    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        nfcPendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun onResume() {
        super.onResume()
        nfcAdapter.enableForegroundDispatch(this, nfcPendingIntent, null, null)
    }

    override fun onPause() {
        nfcAdapter.disableForegroundDispatch(this)
        super.onPause()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
        if(tag != null) {
            val byteId = tag.id
            binding.nfcRead.text = byteId.toHexString()
        }
    }

    fun ByteArray.toHexString(): String {
        val chars = "0123456789ABCDEF"
        val hex = CharArray(this.size * 2)
        for(i in this.indices) {
            val v = this[i].toInt() and 0xff
            hex[i*2] = chars[v shr 4]
            hex[i*2+1] = chars[v and 0xf]
        }
        return String(hex)
    }
}