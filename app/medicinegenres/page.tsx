import CategoryCard from "@/app/components/Category";
import Image from "next/image";
import { redirect } from "next/navigation";
import { getCookies } from "next-client-cookies/server";

export default async function Categories () {
  const cookies = getCookies()
  const token = cookies.get('token')

  async function getData () {
    const res = await fetch('http://localhost:8080/api/medicinegenres', {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
      },
      cache: 'no-cache'
    })
    if (!res.ok) {
      redirect('http://localhost:3000/login')
    }
    return res.json()
  }

  const data = await getData()

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <CategoryCard data={data} />
    </main>
  );
}