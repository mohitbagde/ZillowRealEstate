package com.example.webtechfinal;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Basic extends Fragment
{
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View rootView = paramLayoutInflater.inflate(R.layout.basic_info, paramViewGroup, false);
    return rootView;
  }
}