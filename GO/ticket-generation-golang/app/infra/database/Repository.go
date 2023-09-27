package database

import (
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
	"log"
	"ticket-generation-golang/app/domain/entity"
)

func connectionDataBase() *gorm.DB {
	environmentDataBase := "root:123@tcp(localhost:3306)/bankslips-db?charset=utf8mb4&parseTime=True&loc=Local"
	dataBase, err := gorm.Open(mysql.Open(environmentDataBase), &gorm.Config{})
	if err != nil {
		log.Fatal("Error connecting to database:", err)
	}
	return dataBase
}

func CreateBankSlip(bankSlip entity.BankSlip) {
	dataBase := connectionDataBase()
	dataBase.AutoMigrate(bankSlip)
	dataBase.Create(bankSlip)
}

func GetListBankSlips() []entity.BankSlip {
	dataBase := connectionDataBase()
	var bankSlips []entity.BankSlip
	dataBase.Find(&bankSlips)
	return bankSlips
}

func FindBankSlipById(id string) *entity.BankSlip {
	dataBase := connectionDataBase()
	var bankSlip entity.BankSlip

	if err := dataBase.Where("id = ?", id).First(&bankSlip).Error; err != nil {
		// Retorne nil em caso de erro
		return nil
	}

	// Retorne um ponteiro para bankSlip
	return &bankSlip
}

func UpdateBankSlip(id string) {
	dataBase := connectionDataBase()
	dataBase.Model(&entity.BankSlip{}).Update(id, &entity.BankSlip{})
}

func DeleteBankSlip(id string) {
	dataBase := connectionDataBase()
	dataBase.Delete(&entity.BankSlip{}, id)
}
