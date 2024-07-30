import Image from "next/image";
import CategoryCard from "../components/Category";


export default async function Categories () {
  async function getData () {
    const res = await fetch('http://localhost:8080/api/categories/', { cache: 'no-store' })
    if (!res.ok) {
      throw new Error('Failed to fetch data')
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
