// Generated by view binder compiler. Do not edit!
package com.example.condoapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.condoapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class AnnouncementLogBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final LinearLayout announcement;

  @NonNull
  public final TextView announcementDate;

  @NonNull
  public final TextView announcementTitle;

  private AnnouncementLogBinding(@NonNull RelativeLayout rootView,
      @NonNull LinearLayout announcement, @NonNull TextView announcementDate,
      @NonNull TextView announcementTitle) {
    this.rootView = rootView;
    this.announcement = announcement;
    this.announcementDate = announcementDate;
    this.announcementTitle = announcementTitle;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static AnnouncementLogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AnnouncementLogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.announcement_log, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AnnouncementLogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.announcement;
      LinearLayout announcement = ViewBindings.findChildViewById(rootView, id);
      if (announcement == null) {
        break missingId;
      }

      id = R.id.announcement_date;
      TextView announcementDate = ViewBindings.findChildViewById(rootView, id);
      if (announcementDate == null) {
        break missingId;
      }

      id = R.id.announcement_title;
      TextView announcementTitle = ViewBindings.findChildViewById(rootView, id);
      if (announcementTitle == null) {
        break missingId;
      }

      return new AnnouncementLogBinding((RelativeLayout) rootView, announcement, announcementDate,
          announcementTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
