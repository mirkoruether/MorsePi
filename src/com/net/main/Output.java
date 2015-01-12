package com.net.main;

import com.pi4j.io.gpio.*;

public class Output
{
    public final static int ditLength = 250;
    private GpioPinDigitalOutput led;

    public Output(int pin)
    {
        try
        {
            GpioController gpio = GpioFactory.getInstance();
            String s = "GPIO_";
            if(String.valueOf(pin).length() < 2)
                s += "0";
            s += String.valueOf(pin);
            System.out.println(s);
            led = gpio.provisionDigitalOutputPin((Pin) RaspiPin.class.getField(s).get(null), "MyLed", PinState.LOW);
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
        String[] toCode = code.split(" ");

        for(int i = 0; i < toCode.length; i++)
        {
            System.out.print("morst...[" + (int) (((double) i / (double) toCode.length) * 100) + "%]\r");
            if(toCode[i].equals("/"))
                waitFiveDits();
            else
            {
                for(int j = 0; j < toCode[i].length(); j++)
                {
                    switch(toCode[i].charAt(j))
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

        System.out.println("morst...[100%]");
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

    private void waitFiveDits()
    {
        waitSecs(ditLength * 5);
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
