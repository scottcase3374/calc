package com.starcases.calc;

public class DataWrapper implements Wrapper
{
    public DataWrapper(String data)
    {
        this.data = data;
    }

    public String getData()
    {
        return data;
    }

    @Override
    public String execute(CalcModel model) {
       return getData().trim();
    }

    private String data;
}
