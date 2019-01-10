/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpi_io;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Federico
 */
public class RPI_IO implements IO_Interface{
    
    private DS1307 rtc = null;
    private LTC2309 adc = null;
    private MCP23017 gpio = null;
    
    public RPI_IO() {

        I2CBus i2c;
        try {
            i2c = I2CFactory.getInstance(I2CBus.BUS_1);
            rtc = new DS1307(i2c);
            gpio = new MCP23017(i2c);
            adc = new LTC2309(i2c);
                        
        } catch (I2CFactory.UnsupportedBusNumberException ex) {
            System.out.println("I2C Error. RPI_IO board not active");
            Logger.getLogger(RPI_IO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("I2C Error. RPI_IO board not active");
            Logger.getLogger(RPI_IO.class.getName()).log(Level.SEVERE, null, ex);
        }
        gpio.setOFF_All();
    }
   
    @Override
    public void allRly_off(){
        gpio.setOFF_All();
    }

    @Override
    public void allRly_on(){
        gpio.setON_All();
    }

    @Override
    public int getRlyStatus(){
        int data = 0;
        data = gpio.getRLYS();
        return data;
    }

    @Override
       public void setRly(int r) {
           gpio.setON_Rly(r);
    }

    @Override
    public void resetRly(int r) {
        gpio.setOFF_Rly(r);
    }

    @Override
    public void toggleRly(int r) {
        gpio.toggle_Rly(r);
    }

    public void pulseRly(int r, int t) {
        gpio.pulseON_Rly(r, t);
    }

    @Override
    public void pulseToggle(int r, int time) {
        gpio.pulseToggle(r, time);
    }

    @Override
    public int getInputs() {
        int data = 0;
        data = gpio.getInputs();
        return data;
    }

    @Override
    public int getChannel(int c) {
        int data = 0;
        data = adc.getAnalogIn(c);
        return data;
    }

    @Override
    public String getTime() {
        String time = null;
        time = rtc.getTime();
        return time;
    }

    @Override
    public String getDate() {
        String date = null;
        date = rtc.getDate();
        return date;
    }

    @Override
    public void setTime(String s) {
        rtc.setTime(s);
    }

    @Override
    public void setDate(String s) {
        rtc.setDate(s);
    }

    @Override
    public void blink_1Hz() {
        try {
            rtc.blink();
        } catch (IOException ex) {
            Logger.getLogger(RPI_IO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void out_on() {
        try {
            rtc.out_on();
        } catch (IOException ex) {
            Logger.getLogger(RPI_IO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void out_off() {
        try {
            rtc.out_off();
        } catch (IOException ex) {
            Logger.getLogger(RPI_IO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void writeString(int a, String s) {
        try {
            rtc.writeString(a, s);
        } catch (IOException ex) {
            Logger.getLogger(RPI_IO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String readString(int a) {
        String data = null;
        try {
            data = rtc.readString(a);
        } catch (IOException ex) {
            Logger.getLogger(RPI_IO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @Override
    public void writeInt(int a, int i) {
        try {
            rtc.writeInt(a, i);
        } catch (IOException ex) {
            Logger.getLogger(RPI_IO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int readInt(int a) {
        int data = 0;
        try {
            data = rtc.readInt(a);
        } catch (IOException ex) {
            Logger.getLogger(RPI_IO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @Override
    public void pulseRly(int r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Calendar getCalendarRTC() {
        
        return rtc.getCalendarRTC();
    }

    @Override
    public void setCalendarRTC(Calendar d) {
        rtc.setCalendarRTC(d);
    }
    
    
}
