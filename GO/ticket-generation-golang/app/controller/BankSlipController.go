package controller

import (
	"encoding/json"
	"github.com/go-playground/validator/v10"
	"github.com/gorilla/mux"
	"net/http"
	"ticket-generation-golang/app/domain/entity/request"
	"ticket-generation-golang/app/domain/service"
)

func CreateBankSlips(w http.ResponseWriter, r *http.Request) {

	if r.ContentLength == 0 {
		http.Error(w, "Bankslip not provided in the request body", http.StatusBadRequest)
		return
	}

	var bankSlipRequest request.BankSlipRequest
	_ = json.NewDecoder(r.Body).Decode(&bankSlipRequest)

	validate := validator.New()
	err := validate.Struct(bankSlipRequest)

	if err != nil {
		http.Error(w, "Missing or invalid required fields", http.StatusUnprocessableEntity)
		return
	}

	response := service.CreateBankSlips(bankSlipRequest)

	jsonResponse, err := json.Marshal(response)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusCreated)
	w.Write(jsonResponse)
}

func GetAllBankSlips(w http.ResponseWriter, r *http.Request) {

	response := service.GetAllBankSlips()

	jsonResponse, err := json.Marshal(response)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	w.Write(jsonResponse)
}

func FindBankSlipById(w http.ResponseWriter, r *http.Request) {

	vars := mux.Vars(r)
	id := vars["id"]

	response := service.FindBankSlipById(id)
	if response == nil {
		http.Error(w, "Bankslip not found with the specified id", http.StatusNotFound)
		return
	}

	jsonResponse, err := json.Marshal(response)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	w.Write(jsonResponse)
}
