package controller

import (
	"fmt"
	"net/http"
)

func CreateBankSlips(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Home Page")
}
