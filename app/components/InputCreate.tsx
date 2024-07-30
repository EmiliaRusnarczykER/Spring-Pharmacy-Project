'use client'
import { Card, CardHeader, CardBody, Image, CardFooter, Button, Input } from "@nextui-org/react";
import { useCookies } from "next-client-cookies";
import { useRouter } from "next/navigation";
import { useState } from "react";

export function InputCreate () {
  const [title, setTitle] = useState('')
  const [platform, setPlatform] = useState('')
  const [price, setPrice] = useState(0)

  const cookies = useCookies()
  const token = cookies.get('token')
  const router = useRouter()

  const img = 'https://medlineplus.gov/images/Medicines_share.jpg'

  const handlePriceChange = (e: any) => {
    const value = e.target.value;
    const numberValue = value === '' ? 0 : parseFloat(value);
    if (!isNaN(numberValue)) {
      setPrice(numberValue);
    }
  };

  async function createMedicine () {
    const data = {
      title,
      platform,
      price,
      img
    };

    try {
      const response = await fetch(`http://localhost:8080/api/medicines/`, {
        method: 'POST',
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

  return (
    <div className="space-y-3 px-3 pt-3">
      <Input
        isClearable
        type="text"
        label="Title"
        variant="bordered"
        placeholder="Enter the title"
        onChange={(e) => setTitle(e.target.value)}
        defaultValue=''
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
        defaultValue=''
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
        defaultValue=''
        fullWidth
        isInvalid={false}
      />
      <Button color="primary" onClick={() => createMedicine()}>Create</Button>
    </div>
  )
}