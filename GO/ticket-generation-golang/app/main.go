package main

import (
	"fmt"
	"ticket-generation-golang/app/routes"
)

func main() {
	fmt.Println("Iniciando o servidor Rest em GO")
	routes.HandleRequest()
}
