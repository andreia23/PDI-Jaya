package infra

import (
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
	"log"
	"ticket-generation-golang/app/domain/entity/request"
)

func connectionDataBase() *gorm.DB {
	environmentDataBase := "user:123@tcp(localhost:3306)/bankslips-db?charset=utf8mb4&parseTime=True&loc=Local"
	dataBase, err := gorm.Open(mysql.Open(environmentDataBase), &gorm.Config{})
	if err != nil {
		log.Fatal("Error connecting to database:", err)
	}
	return dataBase
}

func CreateBankSlip(bankSlipRequest request.BankSlipRequest) {
	dataBase := connectionDataBase()
	dataBase.AutoMigrate(bankSlipRequest)
	dataBase.Create(bankSlipRequest)
}

func GetListBankSlips() []request.BankSlipRequest {
	dataBase := connectionDataBase()
	var bankSlips []request.BankSlipRequest
	dataBase.Find(&bankSlips)
	return bankSlips
}

func FindBankSlipById(id string) request.BankSlipRequest {
	dataBase := connectionDataBase()
	var bankSlips request.BankSlipRequest
	dataBase.First(&bankSlips, id)
	return bankSlips
}

func UpdateBankSlip(id string) {
	dataBase := connectionDataBase()
	dataBase.Model(&request.BankSlipRequest{}).Update(id, &request.BankSlipRequest{})
}

func DeleteBankSlip(id string) {
	dataBase := connectionDataBase()
	dataBase.Delete(&request.BankSlipRequest{}, id)
}
