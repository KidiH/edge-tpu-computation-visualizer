package com.google.sps.data;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/** Objects to hold the file information to be displayed in the drop down menu of the
 * all uploaded files
 */
public class LoadFile {
  private long id;
  private String name;
  private String time;
  private String dateTimeString;
  private String zone;
  private String user;
  private boolean userFilesExist;

  public LoadFile(
      long id, 
      String name, 
      String dateTimeString, 
      String zone, 
      String user, 
      boolean userFilesExist) {
        
    this.id = id;
    this.name = name;
    this.time = "";
    this.dateTimeString = dateTimeString;
    this.zone = zone;
    this.user = user;
    this.userFilesExist = userFilesExist;

    this.getTime();
  }

  /** Generates the appropriate time information of the file according to the time zone */
  private void getTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    ZonedDateTime dateTime = ZonedDateTime.parse(dateTimeString, formatter);
    formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
    dateTime = dateTime.withZoneSameInstant(ZoneId.of(zone));
    time += dateTime.format(formatter);

    if (zone.equals("-04:00")) {
      time += " EDT";
    } else if (zone.equals("-09:00")) {
      time += " HDT";
    } else if (zone.equals("-06:00")) {
      time += " MDT";
    } else if (zone.equals("Z")) {
      time += " UTC";
    } else {          
      if (zone.equals("-05:00")) {
        time += " EST";
      } else if (zone.equals("-10:00")) {
        time += " HST";
      } else if (zone.equals("-07:00")) {
        time += " MST";
      } else if (zone.equals("Z")) {
        time += " UTC";
      } else {
        formatter = DateTimeFormatter.ofPattern("z");
        time += " " + dateTime.format(formatter);
      }         
    }
  }
}