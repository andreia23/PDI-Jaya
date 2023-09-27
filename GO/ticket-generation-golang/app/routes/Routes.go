package routes

import (
	"github.com/gorilla/mux"
	"log"
	"net/http"
	"ticket-generation-golang/app/controller"
)

func HandleRequest() {
	endpoint := "/rest/bankslips"
	router := mux.NewRouter()
	router.HandleFunc(endpoint, controller.CreateBankSlips).Methods("POST")
	router.HandleFunc(endpoint, controller.GetAllBankSlips).Methods("GET")
	router.HandleFunc(endpoint+"/{id}", controller.FindBankSlipById).Methods("GET")

	log.Fatal(http.ListenAndServe(":8050", router))
}
