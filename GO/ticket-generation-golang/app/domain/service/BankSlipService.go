package service

import (
	"ticket-generation-golang/app/domain/entity"
	"ticket-generation-golang/app/infra/database"
)

func CreateBankSlips(request entity.BankSlipRequest) entity.BankSlip {
	var bankSlip = entity.BankSlip{
		DueData:      request.DueData,
		TotalInCents: request.TotalInCents,
		Customer:     request.Customer,
		Status:       "PENDING",
	}
	database.CreateBankSlip(bankSlip)
	return bankSlip
}
