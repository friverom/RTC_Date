/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpi_io;

import java.util.Calendar;

/**
 *
 * @author Federico
 */
public interface IO_Interface {
    
    //GPIO method definitions
    public abstract void allRly_off();
    public abstract void allRly_on();
    public abstract int getRlyStatus();
    public abstract void setRly(int r);
    public abstract void resetRly(int r);
    public abstract void toggleRly(int r);
    public abstract void pulseRly(int r);
    public abstract void pulseToggle(int r, int time);
    public abstract int getInputs();
    
    //ADC methods
    public abstract int getChannel(int c);
    
    //RTC methods
    public abstract String getTime();
    public abstract String getDate();
    public abstract Calendar getCalendarRTC();
    public abstract void setTime(String s);
    public abstract void setDate(String s);
    public abstract void setCalendarRTC(Calendar d);
    public abstract void blink_1Hz();
    public abstract void out_on();
    public abstract void out_off();
    public abstract void writeString(int a, String s);
    public abstract String readString(int a);
    public abstract void writeInt(int a, int i);
    public abstract int readInt(int a);
    
    
    
}
