package controller

import (
	"encoding/json"
	"net/http"
	"ticket-generation-golang/app/domain/entity/request"
	"ticket-generation-golang/app/domain/service"
)

func CreateBankSlips(w http.ResponseWriter, r *http.Request) {
	var bankSlipRequest request.BankSlipRequest
	err := json.NewDecoder(r.Body).Decode(&bankSlipRequest)
	if err != nil {
		http.Error(w, "Erro ao decodificar o corpo da requisição", http.StatusBadRequest)
		return
	}

	response := service.CreateBankSlips(bankSlipRequest)

	// Converta a resposta em JSON
	jsonResponse, err := json.Marshal(response)
	if err != nil {
		http.Error(w, "Erro ao converter a resposta em JSON", http.StatusInternalServerError)
		return
	}

	// Defina o cabeçalho Content-Type como application/json
	w.Header().Set("Content-Type", "application/json")

	// Escreva a resposta JSON
	w.Write(jsonResponse)
}
