package service

import (
	_ "fmt"
	"github.com/google/uuid"
	"ticket-generation-golang/app/domain/entity"
	"ticket-generation-golang/app/domain/entity/request"
	"ticket-generation-golang/app/domain/entity/response"
	"ticket-generation-golang/app/infra/database"
)

func CreateBankSlips(request request.BankSlipRequest) response.BankSlipResponse {
	var bankSlip = entity.BankSlip{
		Id:           uuid.New(),
		DueDate:      request.DueData,
		TotalInCents: request.TotalInCents,
		Customer:     request.Customer,
		Status:       "PENDING",
	}
	database.CreateBankSlip(bankSlip)
	return response.BankSlipResponse{
		Id:           bankSlip.Id,
		DueData:      bankSlip.DueDate,
		TotalInCents: bankSlip.TotalInCents,
		Customer:     bankSlip.Customer,
		Status:       bankSlip.Status,
	}
}

func GetAllBankSlips() []response.BankSlipResponse {
	var bankSlips = database.GetListBankSlips()
	var bankSlipsResponses []response.BankSlipResponse

	for _, bankSlip := range bankSlips {
		var bankSlipResponse = response.BankSlipResponse{
			Id:           bankSlip.Id,
			DueData:      bankSlip.DueDate,
			TotalInCents: bankSlip.TotalInCents,
			Customer:     bankSlip.Customer,
			Status:       bankSlip.Status,
		}

		bankSlipsResponses = append(bankSlipsResponses, bankSlipResponse)
	}

	return bankSlipsResponses
}

func FindBankSlipById(id string) *entity.BankSlip {
	var bankSlip = database.FindBankSlipById(id)
	return bankSlip
}

func calculateFine() {

}
