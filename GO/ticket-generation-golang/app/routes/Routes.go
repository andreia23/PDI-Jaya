package routes

import (
	"log"
	"net/http"
	"ticket-generation-golang/app/controller"
)

func HandleRequest() {
	endpoint := "/rest/bankslips"
	http.HandleFunc(endpoint, controller.CreateBankSlips)
	log.Fatal(http.ListenAndServe(":8080", nil))
}
