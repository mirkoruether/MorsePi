package com.net.main;

import com.pi4j.io.gpio.*;

public class Output
{
    public final static int ditLength = 400;
    private GpioPinDigitalOutput led;

    public Output(String pin)
    {
        try
        {
            GpioController gpio = GpioFactory.getInstance();
            led = gpio.provisionDigitalOutputPin((Pin) RaspiPin.class.getField(pin).get(null), "MyLed", PinState.LOW);
            led.setShutdownOptions(true, PinState.LOW);
        } catch(Exception ex)
        {
            Logger.error(ex);
        } catch(Error er)
        {
            Logger.error(er);
            System.exit(0);
        }
    }

    public void morse(String code)
    {
        String[] toCode = code.split("/");

        for(String c : toCode)
            if(c.equals(" "))
                waitSevenDits();
            else
            {
                for(int i = 0; i < c.length(); i++)
                {
                    switch(c.charAt(i))
                    {
                        case '.':
                            dit();
                            break;
                        case '-':
                            dah();
                            break;
                        default:
                            break;
                    }
                    waitDit();
                }
                waitDah();
            }
    }

    private void dit()
    {
        led.high();
        waitDit();
        led.low();
    }

    private void dah()
    {
        led.high();
        waitDah();
        led.low();
    }

    private void waitDit()
    {
        waitSecs(ditLength);
    }

    private void waitDah()
    {
        waitSecs(ditLength * 3);
    }

    private void waitSevenDits()
    {
        waitSecs(ditLength * 7);
    }

    private void waitSecs(long length)
    {
        try
        {
            Thread.sleep(length);
        } catch(Exception ex)
        {
        }
    }
}
