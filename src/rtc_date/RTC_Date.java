/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtc_date;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import rpi_io.RPI_IO;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import rpi_io.DS1307;

/**
 *
 * @author federico
 */
public class RTC_Date {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException, I2CFactory.UnsupportedBusNumberException {
        
        int numArgs = args.length;
        String firstArg = null;
        String secondArg = null;
    
        if (numArgs > 0) {
            firstArg = args[0].toUpperCase();
            if (numArgs > 1) {
                secondArg = args[1].toUpperCase();
            }
        }
    
  //  RPI_IO rpio = new RPI_IO();
  /*   I2CBus i2c = I2CFactory.getInstance(I2CBus.BUS_1);   
     DS1307 rtc = new DS1307(i2c);
     LTC2309 adc = new LTC2309(i2c); */
         
     switch (numArgs) {
         case 0:
             noArgs();
             break;
         case 1:
             oneArg(firstArg);
             break;
         case 2:
             twoArgs(firstArg,secondArg);
         default:
             
     }
     
    }
    
    private static void noArgs() throws IOException, I2CFactory.UnsupportedBusNumberException {
        
        I2CBus i2c = I2CFactory.getInstance(I2CBus.BUS_1);   
        DS1307 rtc = new DS1307(i2c);
       // RPI_IO rpio = r;
        Calendar date=Calendar.getInstance();
        Calendar rtc_date=rtc.getCalendarRTC();
        
        System.out.println("Linux Date: " + date.getTime());
        System.out.println("RTC   Date: " + rtc_date.getTime());

    }
    
    private static void oneArg(String arg1) throws IOException, I2CFactory.UnsupportedBusNumberException{
        
      //  RPI_IO rpio = r;
        I2CBus i2c = I2CFactory.getInstance(I2CBus.BUS_1);   
        DS1307 rtc = new DS1307(i2c);
        Calendar date=Calendar.getInstance();
        
        switch(arg1){
            case "RTC":
                System.out.println("RTC Date: " + rtc.getCalendarRTC().getTime());
                break;
            case "SYS":
                System.out.println("Linux Date: " + date.getTime());
                break;
            default:
                System.out.println("Comando invalido.");
        }
       
    }
    
    private static void twoArgs(String arg1, String arg2) throws IOException, InterruptedException, I2CFactory.UnsupportedBusNumberException{
        
      //  RPI_IO rpio = r;
        I2CBus i2c = I2CFactory.getInstance(I2CBus.BUS_1);   
        DS1307 rtc = new DS1307(i2c);
        Process p;
        Calendar SYS=Calendar.getInstance();
        Calendar RTC=rtc.getCalendarRTC();
        
        switch(arg1){
            case "RTC":
                if(arg2.equalsIgnoreCase("SYS")){
                    p = Runtime.getRuntime().exec("sudo date --set="+sysDate(RTC));
                    p.waitFor();
                    p.destroy();
                    
                    p = Runtime.getRuntime().exec("sudo date --set="+sysTime(RTC));
                    p.waitFor();
                    p.destroy();
                    
                } else {
                System.out.println("Comando invalido");    
                }
                break;
            case "SYS":
                if(arg2.equalsIgnoreCase("RTC")){
                    rtc.setCalendarRTC(SYS);
                } else {
                System.out.println("Comando Invalido");
                } 
                break;
         /*   case "ADC":
                int data = r.getChannel(Integer.parseInt(arg2));
                System.out.println("ADC 0: "+data);
                break; */
            default:
                System.out.println("Comando Invalido");
        }
    }
    
    private static String sysDate(Calendar d) {

        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        // Date date = new Date();
        Date date = d.getTime();
        String Date = df.format(date);
        
        return Date;
    }
    
    private static String sysTime(Calendar t) {

        DateFormat tf = new SimpleDateFormat("HH:mm:ss");
        //  Date time = new Date();
        Date time = t.getTime();
        String Time = tf.format(time);
        
        return Time;
    }
    }
    

