package com.example.vukhachoi.musicapp;

/**
 * Created by Vu Khac Hoi on 8/23/2017.
 */

public class baihat {
    public baihat(String _tenbaihat, String _tentacgia) {
        this._tenbaihat = _tenbaihat;
        this._tentacgia = _tentacgia;
    }

    public baihat(int file, String _tenbaihat, String _tentacgia) {
        this.file = file;
        this._tenbaihat = _tenbaihat;
        this._tentacgia = _tentacgia;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    int file;
    public String get_tenbaihat() {
        return _tenbaihat;
    }

    public String get_tentacgia() {
        return _tentacgia;
    }

    public void set_tenbaihat(String _tenbaihat) {
        this._tenbaihat = _tenbaihat;
    }

    public void set_tentacgia(String _tentacgia) {
        this._tentacgia = _tentacgia;
    }

    String _tenbaihat;
    String _tentacgia;
}
