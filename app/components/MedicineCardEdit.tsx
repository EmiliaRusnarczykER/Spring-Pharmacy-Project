'use client'
import { Card, CardHeader, CardBody, Image, CardFooter, Button, Input } from "@nextui-org/react";
import { useCookies } from "next-client-cookies";
import { useRouter } from "next/navigation";
import { useState } from "react";

interface MedicineProps {
  medicine: {
    id: number;
    title: string;
    platform: string;
    price: number;
    img: string;
  };
}

interface InputProps {
  medicineId: number,
  medicineTitle: string,
  medicinePlatform: string,
  medicinePrice: number,
  medicineImg: string,
}

export function MedicineCardEdit ({ medicine }: MedicineProps) {
  const cookies = useCookies()
  const token = cookies.get('token')
  const router = useRouter()

  function InputUpdate ({ medicineId, medicineTitle, medicinePlatform, medicinePrice, medicineImg }: InputProps) {
    //const [Id, setId] = useState(medicineId)
    const [title, setTitle] = useState(medicineTitle)
    const [platform, setPlatform] = useState(medicinePlatform)
    const [price, setPrice] = useState(medicinePrice)
    const img = medicineImg

    const handlePriceChange = (e: any) => {
      const value = e.target.value;
      const numberValue = value === '' ? 0 : parseFloat(value);
      if (!isNaN(numberValue)) {
        setPrice(numberValue);
      }
    };

    async function updateMedicine (medicineId: number) {
      const data = {
        title,
        platform,
        price,
        img
      };

      try {
        const response = await fetch(`http://localhost:8080/api/medicines/id=${medicineId}`, {
          method: 'PUT',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
          cache: 'force-cache',
          body: JSON.stringify(data),
        });

        console.log(data)
        if (response.ok) {
          const result = await response.json();
          console.log('Success:', result);
          router.refresh()
          return result
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };
    async function deleteMedicine (medicineId: number) {
      try {
        const res = await fetch(`http://localhost:8080/api/medicines/id=${medicineId}`, {
          method: 'DELETE',
          headers: {
            'Authorization': `Bearer ${token}`,
          },
          cache: 'force-cache'
        });
        if (res.ok) {
          const result = await res.text();
          console.log('Success:', result);
          router.refresh()
        }
      } catch (error) {
        console.error('Error:', error);
      }
    }

    return (
      <div className="space-y-3 px-3 pt-3">
        <Input
          isClearable
          type="text"
          label="Title"
          variant="bordered"
          placeholder="Enter the title"
          onChange={(e) => setTitle(e.target.value)}
          defaultValue={medicineTitle}
          fullWidth
          isInvalid={false}
        />
        <Input
          isClearable
          type="text"
          label="Platform"
          variant="bordered"
          placeholder="Analgesics, Amoxicillin..."
          onChange={(e) => setPlatform(e.target.value)}
          defaultValue={medicinePlatform}
          fullWidth
          isInvalid={false}
        />
        <Input
          isClearable
          type="text"
          label="Price"
          variant="bordered"
          placeholder="$$$"
          onChange={handlePriceChange}
          defaultValue={medicinePrice.toString()}
          fullWidth
          isInvalid={false}
        />
        <div className="justify-between flex pb-3 px-3">
          <Button color="primary" onClick={() => updateMedicine(medicine.id)}>Update</Button>
          <Button color="danger" onClick={() => deleteMedicine(medicine.id)}>Remove</Button>
        </div>
      </div>
    )
  }



  return (
    <Card key={medicine.id} isFooterBlurred className="border-none m-4">
      <InputUpdate medicineId={medicine.id} medicineImg={medicine.img} medicineTitle={medicine.title} medicinePlatform={medicine.platform} medicinePrice={medicine.price} />
      <Image
        alt="Card background"
        className="object-cover rounded-xl"
        src={medicine.img }
        width={270}
      />
      <CardFooter className="justify-between flex-col  border-1 overflow-hidden py-1 absolute before:rounded-xl rounded-large bottom-1 w-[calc(100%_-_8px)] shadow-small ml-1 z-10">
        <h4 className="font-bold text-large">{medicine.title}</h4>
        <p className="text-cyan-400 font-extrabold text-tiny uppercase">{medicine.platform}</p>
        <small className="text-white">{medicine.price} €</small>
      </CardFooter>
    </Card>
  )
}