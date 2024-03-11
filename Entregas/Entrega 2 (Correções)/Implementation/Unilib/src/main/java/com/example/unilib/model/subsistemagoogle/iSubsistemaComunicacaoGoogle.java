package com.example.unilib.model.subsistemagoogle;

import java.util.Map;

public interface iSubsistemaComunicacaoGoogle {


    public Map<String, Boolean> doGoogleSignIn(Map<String, String> payload);
}